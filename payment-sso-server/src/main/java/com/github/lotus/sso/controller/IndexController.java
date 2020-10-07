package com.github.lotus.sso.controller;

import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.sso.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping
public class IndexController {
    private final AccountService accountService;

    @PostMapping("/join")
    public void createAccount(@RequestBody JoinRo ro) {
        accountService.createAccount(ro);
    }
}
