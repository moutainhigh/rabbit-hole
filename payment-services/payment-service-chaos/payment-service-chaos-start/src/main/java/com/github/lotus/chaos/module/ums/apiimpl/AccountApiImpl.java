package com.github.lotus.chaos.module.ums.apiimpl;

import com.github.lotus.chaos.api.AccountApi;
import com.github.lotus.chaos.module.ums.entity.Account;
import com.github.lotus.chaos.module.ums.mapstruct.AccountMapping;
import com.github.lotus.chaos.module.ums.service.AccountService;
import com.github.lotus.chaos.vo.UserDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    private final AccountMapping mapping;

    @Override
    public UserDetailVo getUser(@RequestParam("username") String username) {
        Optional<Account> accountOpt = service.getAccountByUsername(username);
        return accountOpt.map(mapping::asUserDetailVo).orElse(null);
    }
}
