package com.github.lotus.chaos.module.ums.service.impl;

import com.github.lotus.chaos.module.ums.entity.Account;
import com.github.lotus.chaos.module.ums.mapper.AccountMapper;
import com.github.lotus.chaos.module.ums.service.AccountService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

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

    @Override
    public Optional<Account> getAccountByUsername(String username) {
        return lambdaQuery().eq(Account::getUsername, username).oneOpt();
    }
}
