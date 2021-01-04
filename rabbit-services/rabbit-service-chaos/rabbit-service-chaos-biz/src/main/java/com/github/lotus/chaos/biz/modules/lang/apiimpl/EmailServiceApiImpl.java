package com.github.lotus.chaos.biz.modules.lang.apiimpl;

import com.github.lotus.chaos.api.modules.lang.EmailServiceApi;
import com.github.lotus.chaos.biz.modules.lang.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/1/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class EmailServiceApiImpl implements EmailServiceApi {
    private final EmailService service;

    @Override
    public boolean validVerifyCode(String email, String verifyCode) {
        return service.validVerifyCode(email, verifyCode);
    }

    @Override
    public void sendVerifyCode(String email) {
        service.sendVerifyCode(email);
    }
}
