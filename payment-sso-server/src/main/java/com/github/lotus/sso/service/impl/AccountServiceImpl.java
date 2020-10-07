package com.github.lotus.sso.service.impl;

import com.github.lotus.chaos.modules.ums.api.AccountApi;
import com.github.lotus.chaos.modules.ums.ro.CreateAccountRo;
import com.github.lotus.sso.mapstruct.AccountMapping;
import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.sso.service.AccountService;
import in.hocg.boot.web.servlet.SpringServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccountServiceImpl implements AccountService {
    private final AccountApi api;
    private final AccountMapping mapping;


    @Override
    public void createAccount(JoinRo ro) {
        CreateAccountRo createAccountRo = mapping.asCreateAccountRo(ro);
        createAccountRo.setCreatedIp(SpringServletContext.getClientIp().orElse(null));
        api.createAccount(createAccountRo);
    }
}
