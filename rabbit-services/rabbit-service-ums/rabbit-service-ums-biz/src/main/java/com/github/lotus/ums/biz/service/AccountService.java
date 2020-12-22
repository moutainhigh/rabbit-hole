package com.github.lotus.ums.biz.service;

import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.ums.biz.entity.Account;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;
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

    UserDetailVo createAccount(CreateAccountRo ro);

    UserDetailVo getUserDetailVoByUsername(String username);

    UserDetailVo getUserDetailVoByUsernameOrEmailOrPhone(String unique);

    Optional<Account> getAccountByUsernameOrEmailOrPhone(String unique);

    List<Account> listAccountByAccountId(List<Long> values);

    String getToken(String username);

    String getUsername(String token);

    UserDetailVo getUserByPhone(String phone);

    List<AccountVo> listAccountVoByAccountId(List<Long> id);
}
