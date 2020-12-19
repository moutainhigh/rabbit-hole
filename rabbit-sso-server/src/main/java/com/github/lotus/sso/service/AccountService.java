package com.github.lotus.sso.service;

import com.github.lotus.sso.pojo.ro.JoinAccountRo;
import com.github.lotus.sso.pojo.ro.LoginRo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface AccountService {

    String login(LoginRo ro);

    String join(JoinAccountRo ro);
}
