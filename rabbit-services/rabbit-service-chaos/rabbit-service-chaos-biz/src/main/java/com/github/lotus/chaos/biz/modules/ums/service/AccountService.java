package com.github.lotus.chaos.biz.modules.ums.service;

import com.github.lotus.chaos.api.modules.ums.pojo.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.pojo.vo.UserDetailVo;
import com.github.lotus.chaos.biz.modules.ums.entity.Account;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import org.springframework.transaction.annotation.Transactional;

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
}
