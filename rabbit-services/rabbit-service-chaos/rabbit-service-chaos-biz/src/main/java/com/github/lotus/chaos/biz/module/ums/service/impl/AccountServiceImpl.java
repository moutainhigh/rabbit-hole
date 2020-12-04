package com.github.lotus.chaos.biz.module.ums.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.github.lotus.chaos.api.modules.ums.api.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.api.vo.UserDetailVo;
import com.github.lotus.chaos.biz.module.ums.entity.Account;
import com.github.lotus.chaos.biz.module.ums.mapper.AccountMapper;
import com.github.lotus.chaos.biz.module.ums.mapstruct.AccountMapping;
import com.github.lotus.chaos.biz.module.ums.service.AccountService;
import com.github.lotus.chaos.module.ums.utils.Avatars;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.oss.autoconfigure.core.OssFileService;
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
    private final OssFileService ossFileService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAccount(CreateAccountRo ro) {
        LocalDateTime createdAt = LocalDateTime.now();

        Account entity = mapping.asAccount(ro);
        entity.setNickname(ro.getUsername());
        entity.setCreatedAt(createdAt);
        validInsert(entity);

        // 更新头像
        final Long entityId = entity.getId();
        final File file = Avatars.getAvatarAsPath(entityId).toFile();
        String avatarUrl = ossFileService.upload(file, file.getName());
        final Account update = new Account()
            .setId(entityId)
            .setAvatar(avatarUrl);
        this.validUpdateById(update);
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

    private Optional<Account> getAccountByUsername(String username) {
        return lambdaQuery().eq(Account::getUsername, username).oneOpt();
    }

    private Optional<Account> getAccountByPhone(String phone) {
        return lambdaQuery().eq(Account::getPhone, phone).oneOpt();
    }

    @Override
    public void validEntity(Account entity) {
        super.validEntity(entity);
        String phone = entity.getPhone();
        String username = entity.getUsername();
        if (Objects.nonNull(username)) {
            Assert.isFalse(getAccountByUsername(username).isPresent(), "该用户名已被注册");
        }
        if (Objects.nonNull(phone)) {
            Assert.isFalse(getAccountByPhone(phone).isPresent(), "该手机号已被注册");
        }

    }
}
