package com.github.lotus.sso.controller;

import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.sso.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndexController {
    private final AccountService accountService;

    @PostMapping("/join")
    public void createAccount(@Validated @ModelAttribute JoinRo ro) {
        accountService.createAccount(ro);
    }
}
