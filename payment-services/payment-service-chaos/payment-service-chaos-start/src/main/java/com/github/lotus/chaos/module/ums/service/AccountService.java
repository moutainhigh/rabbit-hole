package com.github.lotus.chaos.module.ums.service;

import com.github.lotus.chaos.module.ums.entity.Account;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [用户模块] 账号表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
public interface AccountService extends AbstractService<Account> {

    Optional<Account> getAccountByUsername(String username);
}
