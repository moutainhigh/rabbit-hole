package com.github.lotus.chaos.module.ums.service;

import com.github.lotus.chaos.module.ums.entity.Account;
import com.github.lotus.chaos.modules.ums.ro.CreateAccountRo;
import com.github.lotus.chaos.modules.ums.vo.UserDetailVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [用户模块] 账号表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
public interface AccountService extends AbstractService<Account> {

    void createAccount(CreateAccountRo ro);

    UserDetailVo getUser(String username);
}
