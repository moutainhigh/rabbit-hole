package com.github.lotus.chaos.biz.module.ums.service;

import com.github.lotus.chaos.api.modules.ums.api.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.api.vo.UserDetailVo;
import com.github.lotus.chaos.biz.module.ums.entity.Account;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

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

    List<Account> listAccountByAccountId(List<Long> values);
}
