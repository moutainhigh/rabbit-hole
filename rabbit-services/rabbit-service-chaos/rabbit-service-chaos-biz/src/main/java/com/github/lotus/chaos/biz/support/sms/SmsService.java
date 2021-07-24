package com.github.lotus.chaos.biz.support.sms;


import com.github.lotus.chaos.biz.pojo.ro.SendSmsCodeRo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SmsService {

    Long sendSmsCode(SendSmsCodeRo qo);

    boolean validSmsCode(String phone, String smsCode);
}
