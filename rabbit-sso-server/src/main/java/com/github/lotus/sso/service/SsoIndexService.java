package com.github.lotus.sso.service;

import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.sso.pojo.ro.SendSmsCodeRo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SsoIndexService {

    void createAccount(JoinRo ro);

    void sendSmsCode(SendSmsCodeRo ro);
}
