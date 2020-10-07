package com.github.lotus.chaos.module.ums.apiimpl;

import com.github.lotus.chaos.module.ums.service.AccountService;
import com.github.lotus.chaos.modules.ums.api.AccountApi;
import com.github.lotus.chaos.modules.ums.ro.CreateAccountRo;
import com.github.lotus.chaos.modules.ums.vo.UserDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public UserDetailVo getUser(@RequestParam("username") String username) {
        return service.getUser(username);
    }

    @Override
    public void createAccount(@RequestBody CreateAccountRo ro) {
        service.createAccount(ro);
    }
}
