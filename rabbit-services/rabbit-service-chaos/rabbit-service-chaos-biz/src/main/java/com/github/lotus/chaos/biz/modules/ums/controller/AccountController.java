package com.github.lotus.chaos.biz.modules.ums.controller;


import com.github.lotus.chaos.biz.modules.ums.entity.Account;
import com.github.lotus.chaos.biz.modules.ums.service.AccountService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.utils.web.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * [用户模块] 账号表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    @UseLogger("获取用户头像")
    @ResponseBody
    @GetMapping("/{username}:avatar")
    public ResponseEntity<?> getAvatarUrl(@PathVariable String username) {
        return ResponseUtils.preview(service.getAccountByUsernameOrEmailOrPhone(username)
            .map(Account::getAvatar).orElse(null));
    }
}

