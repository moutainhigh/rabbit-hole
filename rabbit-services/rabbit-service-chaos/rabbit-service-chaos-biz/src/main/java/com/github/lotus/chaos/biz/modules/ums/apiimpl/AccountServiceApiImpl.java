package com.github.lotus.chaos.biz.modules.ums.apiimpl;

import com.github.lotus.chaos.api.modules.ums.AccountServiceApi;
import com.github.lotus.chaos.api.modules.ums.pojo.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.pojo.vo.UserDetailVo;
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
public class AccountServiceApiImpl implements AccountServiceApi {
    private final AccountService service;

    @Override
    public UserDetailVo getUser(String username) {
        return service.getUserDetailVo(username);
    }

    @Override
    public UserDetailVo getUserByPhone(String phone) {
        return service.getUserByPhone(phone);
    }

    @Override
    public UserDetailVo createAccount(CreateAccountRo ro) {
        return service.createAccount(ro);
    }

    @Override
    public String getToken(String username) {
        return service.getToken(username);
    }

    @Override
    public String getUsername(String token) {
        return service.getUsername(token);
    }
}
