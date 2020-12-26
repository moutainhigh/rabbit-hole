package com.github.lotus.ums.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.github.lotus.chaos.api.modules.com.FileServiceApi;
import com.github.lotus.common.utils.JwtUtils;
import com.github.lotus.common.utils.RabbitUtils;
import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.ro.InsertSocialRo;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.ums.biz.entity.Account;
import com.github.lotus.ums.biz.mapper.AccountMapper;
import com.github.lotus.ums.biz.mapstruct.AccountMapping;
import com.github.lotus.ums.biz.pojo.vo.AccountComplexVo;
import com.github.lotus.ums.biz.service.AccountService;
import com.github.lotus.ums.biz.service.SocialService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final FileServiceApi fileServiceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo createAccount(CreateAccountRo ro) {
        String username = ro.getUsername();
        String nickname = ro.getNickname();
        String avatar = ro.getAvatar();
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

        Account entity = new Account()
            .setNickname(nickname)
            .setUsername(username)
            .setPhone(phone)
            .setEmail(email)
            .setAvatar(avatar)
            .setPassword(password)
            .setCreatedIp(createdIp)
            .setCreatedAt(createdAt);
        ValidUtils.isTrue(validInsert(entity), "系统繁忙，注册失败");
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

        final Account update = new Account()
            .setId(entityId);

        // 设置默认头像，如果没有指定头像的话
        if (Strings.isBlank(avatar)) {
            update.setAvatar(fileServiceApi.getAvatarUrl(entityId));
        }

        // 设置默认账号，如果没有指定账号的话
        if (isUseDefaultUsername) {
            username = RabbitUtils.getDefaultUsername(entityId);
            update.setUsername(username);
        }

        boolean isOk = this.validUpdateById(update);
        ValidUtils.isTrue(isOk, "系统繁忙，注册失败");
        return getUserDetailVoByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo getUserDetailVoByUsername(String username) {
        return getAccountByUsername(username)
            .map(mapping::asUserDetailVo).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo getUserDetailVoByUsernameOrEmailOrPhone(String unique) {
        return getAccountByUsernameOrEmailOrPhone(unique)
            .map(mapping::asUserDetailVo).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<Account> getAccountByUsernameOrEmailOrPhone(String unique) {
        return lambdaQuery()
            .or().eq(Account::getUsername, unique)
            .or().eq(Account::getEmail, unique)
            .or().eq(Account::getPhone, unique).oneOpt();
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

    @Override
    public List<AccountVo> listAccountVoByAccountId(List<Long> id) {
        return LangUtils.toList(this.listAccountByAccountId(id), mapping::asAccountVo);
    }

    @Override
    public AccountComplexVo getAccountVoById(Long userId) {
        Account entity = baseMapper.selectById(userId);
        return mapping.asComplex(entity);
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
