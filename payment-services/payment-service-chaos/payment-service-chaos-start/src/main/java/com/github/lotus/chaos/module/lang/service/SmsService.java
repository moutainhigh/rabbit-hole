package com.github.lotus.chaos.module.lang.service;

import com.github.lotus.chaos.module.lang.pojo.ro.SendSmsCodeRo;
import lombok.NonNull;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SmsService {

    void sendSmsCode(SendSmsCodeRo qo);

    boolean validSmsCode(@NonNull String phone, @NonNull String smsCode);
}
