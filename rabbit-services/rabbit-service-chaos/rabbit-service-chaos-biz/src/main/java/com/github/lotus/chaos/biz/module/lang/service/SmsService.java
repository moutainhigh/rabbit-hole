package com.github.lotus.chaos.biz.module.lang.service;


import com.github.lotus.chaos.biz.module.lang.pojo.ro.SendSmsCodeRo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SmsService {

    void sendSmsCode(SendSmsCodeRo qo);
}
