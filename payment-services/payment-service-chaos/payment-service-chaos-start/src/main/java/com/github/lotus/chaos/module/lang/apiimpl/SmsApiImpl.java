package com.github.lotus.chaos.module.lang.apiimpl;

import com.github.lotus.chaos.module.lang.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.module.lang.service.SmsService;
import com.github.lotus.chaos.modules.lang.api.SmsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class SmsApiImpl implements SmsApi {
    private final SmsService smsService;

    @Override
    public boolean validSmsCode(String phone, String smsCode) {
        return smsService.validSmsCode(phone, smsCode);
    }

    @Override
    public void sendSmsCode(String phone) {
        SendSmsCodeRo ro = new SendSmsCodeRo();
        ro.setPhone(phone);
        smsService.sendSmsCode(ro);
    }
}
