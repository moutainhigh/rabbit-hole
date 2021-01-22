package com.github.lotus.ums.biz.service;

import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountEmailRo;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountPhoneRo;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountRo;
import com.github.lotus.ums.biz.pojo.vo.AccountComplexVo;
import com.github.lotus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
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
public interface AccountService extends AbstractService<User> {

    UserDetailVo createAccount(CreateAccountRo ro);

    UserDetailVo getUserDetailVoByUsername(String username);

    UserDetailVo getUserDetailVoByUsernameOrEmailOrPhone(String unique);

    Optional<User> getAccountByUsernameOrEmailOrPhone(String unique);

    List<User> listAccountByAccountId(List<Long> values);

    String getToken(String username);

    String getUsername(String token);

    UserDetailVo getUserByPhone(String phone);

    List<AccountVo> listAccountVoByAccountId(List<Long> id);

    AccountVo getAccountVoById(Long userId);

    AccountComplexVo getComplexById(Long userId);

    Long updateAccount(Long userId, UpdateAccountRo ro);

    Long updatePhone(UpdateAccountPhoneRo ro);

    Long updateEmail(UpdateAccountEmailRo ro);

    Optional<User> getByUsername(String username);

    List<AuthorityTreeNodeVo> listAuthorityCodeByProjectSnAndUserId(String projectSn, Long userId);
}
