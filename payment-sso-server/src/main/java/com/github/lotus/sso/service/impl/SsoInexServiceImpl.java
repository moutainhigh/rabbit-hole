package com.github.lotus.sso.service.impl;

import com.github.lotus.chaos.modules.lang.api.SmsApi;
import com.github.lotus.chaos.modules.ums.api.AccountApi;
import com.github.lotus.chaos.modules.ums.api.ro.CreateAccountRo;
import com.github.lotus.sso.mapstruct.AccountMapping;
import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.sso.pojo.ro.SendSmsCodeRo;
import com.github.lotus.sso.service.SsoIndexService;
import in.hocg.boot.web.servlet.SpringServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SsoInexServiceImpl implements SsoIndexService {
    private final AccountApi accountApi;
    private final SmsApi smsApi;
    private final AccountMapping mapping;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void createAccount(JoinRo ro) {
        ro.setPassword(passwordEncoder.encode(ro.getPassword()));
        CreateAccountRo createAccountRo = mapping.asCreateAccountRo(ro);
        createAccountRo.setCreatedIp(SpringServletContext.getClientIp().orElse(null));
        accountApi.createAccount(createAccountRo);
    }

    @Override
    public void sendSmsCode(SendSmsCodeRo ro) {
        smsApi.sendSmsCode(ro.getPhone());
    }
}
