package com.github.lotus.chaos.biz.modules.ums.apiimpl;

import com.github.lotus.chaos.api.modules.ums.api.AccountApi;
import com.github.lotus.chaos.api.modules.ums.api.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.api.vo.UserDetailVo;
import com.github.lotus.chaos.biz.modules.ums.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class AccountApiImpl implements AccountApi {
    private final AccountService service;

    @Override
    public UserDetailVo getUser(String username) {
        return service.getUser(username);
    }

    @Override
    public void createAccount(CreateAccountRo ro) {
        service.createAccount(ro);
    }
}
