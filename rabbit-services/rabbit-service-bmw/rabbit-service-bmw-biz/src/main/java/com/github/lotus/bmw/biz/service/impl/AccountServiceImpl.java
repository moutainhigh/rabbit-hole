package com.github.lotus.bmw.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.github.lotus.bmw.biz.entity.Account;
import com.github.lotus.bmw.biz.mapper.AccountMapper;
import com.github.lotus.bmw.biz.mapstruct.AccountMapping;
import com.github.lotus.bmw.biz.pojo.dto.CreateAccountDto;
import com.github.lotus.bmw.biz.service.AccountService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [支付模块] 支付账户表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccountServiceImpl extends AbstractServiceImpl<AccountMapper, Account> implements AccountService {
    private final AccountMapping mapping;

    @Override
    public Optional<Account> getAccount(Long userId, Long accessMchId, Long paymentMchId, String useScenes) {
        return lambdaQuery().eq(Account::getUserId, userId).eq(Account::getAccessMchId, accessMchId)
            .eq(Account::getPaymentMchId, paymentMchId).eq(Account::getUseScenes, useScenes).oneOpt();
    }

    @Override
    public Account createAccount(CreateAccountDto dto) {
        Account entity = mapping.asAccount(dto);
        entity.setCreatedAt(LocalDateTime.now());
        Assert.isTrue(this.validInsert(entity));
        return entity;
    }
}
