package com.github.lotus.chaos.biz.support.email;

/**
 * Created by hocgin on 2021/1/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface EmailService {

    boolean validVerifyCode(String email, String verifyCode);

    void sendVerifyCode(String email);
}
