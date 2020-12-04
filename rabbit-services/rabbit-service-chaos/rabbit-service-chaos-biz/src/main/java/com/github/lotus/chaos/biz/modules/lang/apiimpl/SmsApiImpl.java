package com.github.lotus.chaos.biz.modules.lang.apiimpl;

import com.github.lotus.chaos.api.modules.lang.api.SmsApi;
import com.github.lotus.chaos.biz.modules.lang.manager.SmsManager;
import com.github.lotus.chaos.biz.modules.lang.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.biz.modules.lang.service.SmsService;
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
    private final SmsService service;

    @Override
    public boolean validSmsCode(String phone, String smsCode) {
        return service.validSmsCode(phone, smsCode);
    }

    @Override
    public void sendSmsCode(String phone) {
        service.sendSmsCode(new SendSmsCodeRo()
            .setPhone(phone));
    }
}
