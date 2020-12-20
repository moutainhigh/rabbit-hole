package com.github.lotus.chaos.biz.modules.ums.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.github.lotus.chaos.api.modules.ums.pojo.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.pojo.ro.InsertSocialRo;
import com.github.lotus.chaos.api.modules.ums.pojo.vo.UserDetailVo;
import com.github.lotus.chaos.biz.modules.ums.entity.Account;
import com.github.lotus.chaos.biz.modules.ums.mapper.AccountMapper;
import com.github.lotus.chaos.biz.modules.ums.mapstruct.AccountMapping;
import com.github.lotus.chaos.biz.modules.ums.service.AccountService;
import com.github.lotus.chaos.biz.modules.ums.service.SocialService;
import com.github.lotus.chaos.biz.modules.ums.utils.Avatars;
import com.github.lotus.common.utils.JwtUtils;
import com.github.lotus.common.utils.RabbitUtils;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.oss.autoconfigure.core.OssFileService;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * [用户模块] 账号表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccountServiceImpl extends AbstractServiceImpl<AccountMapper, Account>
    implements AccountService {
    private final AccountMapping mapping;
    private final SocialService socialService;
    private final OssFileService ossFileService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo createAccount(CreateAccountRo ro) {
        String username = ro.getUsername();
        String nickname = ro.getNickname();
        List<CreateAccountRo.SocialItem> socials = ro.getSocials();
        boolean isUseDefaultUsername = StrUtil.isBlank(username);
        boolean isUseDefaultNickname = StrUtil.isBlank(nickname);
        nickname = LangUtils.getOrDefault(nickname, "暂未设置");
        username = LangUtils.getOrDefault(username, RandomUtil.randomString(18));

        String phone = ro.getPhone();
        String email = ro.getEmail();
        String password = LangUtils.getOrDefault(ro.getPassword(), IdUtil.fastSimpleUUID());
        String createdIp = ro.getCreatedIp();

        LocalDateTime createdAt = LocalDateTime.now();

        Account entity = new Account();
        entity.setNickname(nickname);
        entity.setUsername(username);
        entity.setPhone(phone);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setCreatedIp(createdIp);
        entity.setCreatedAt(createdAt);
        validInsert(entity);
        final Long entityId = entity.getId();

        // 关联社交账号，如果有的话
        if (CollectionUtil.isNotEmpty(socials)) {
            for (CreateAccountRo.SocialItem item : socials) {
                socialService.insertOne(new InsertSocialRo()
                    .setSocialType(item.getSocialType())
                    .setSocialId(item.getSocialId())
                    .setAccountId(entityId));
            }
        }

        // 更新头像
        final File file = Avatars.getAvatarAsPath(entityId).toFile();
        String avatarUrl = ossFileService.upload(file, file.getName());
        final Account update = new Account()
            .setId(entityId)
            .setAvatar(avatarUrl);
        if (isUseDefaultUsername) {
            username = RabbitUtils.getDefaultUsername(entityId);
            update.setUsername(username);
        }

        this.validUpdateById(update);
        return getUser(username);
    }

    public static void main(String[] args) {
        System.out.println(IdUtil.fastSimpleUUID());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo getUser(String username) {
        return getAccountByUsername(username)
            .map(mapping::asUserDetailVo).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Account> listAccountByAccountId(List<Long> values) {
        if (CollectionUtil.isEmpty(values)) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(Account::getId, values).list();
    }

    @Override
    public String getToken(String username) {
        return JwtUtils.encode(username);
    }

    @Override
    public String getUsername(String token) {
        return JwtUtils.decode(token);
    }

    @Override
    public UserDetailVo getUserByPhone(String phone) {
        return getAccountByPhone(phone)
            .map(mapping::asUserDetailVo)
            .orElse(null);
    }

    private Optional<Account> getAccountByUsername(String username) {
        return lambdaQuery().eq(Account::getUsername, username).oneOpt();
    }

    private Optional<Account> getAccountByPhone(String phone) {
        return lambdaQuery().eq(Account::getPhone, phone).oneOpt();
    }

    @Override
    public void validEntity(Account entity) {
        super.validEntity(entity);
        Long id = entity.getId();
        String phone = entity.getPhone();
        String username = entity.getUsername();
        boolean isInsert = Objects.isNull(id);

        if (isInsert) {
            if (Objects.nonNull(username)) {
                Assert.isFalse(getAccountByUsername(username).isPresent(), "该用户名已被注册");
            }
            if (Objects.nonNull(phone)) {
                Assert.isFalse(getAccountByPhone(phone).isPresent(), "该手机号已被注册");
            }
        }

    }
}
