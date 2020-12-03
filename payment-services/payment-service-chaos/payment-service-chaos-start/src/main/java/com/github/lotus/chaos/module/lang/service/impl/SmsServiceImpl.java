package com.github.lotus.chaos.module.lang.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.github.lotus.chaos.module.lang.manager.sms.SmsManager;
import com.github.lotus.chaos.module.lang.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.module.lang.service.SmsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SmsServiceImpl implements SmsService {
    private final SmsManager smsManager;

    @Override
    public void sendSmsCode(SendSmsCodeRo qo) {
        final String phone = qo.getPhone();
        Assert.notBlank(phone, "手机号不能为空");
        smsManager.sendSmsCode(phone, RandomUtil.randomNumbers(6));
    }

    @Override
    public boolean validSmsCode(@NonNull String phone, @NonNull String smsCode) {
        return smsManager.validSmsCode(phone, smsCode);
    }
}
