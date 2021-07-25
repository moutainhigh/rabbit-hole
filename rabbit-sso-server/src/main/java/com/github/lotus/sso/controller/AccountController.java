package com.github.lotus.sso.controller;

import com.github.lotus.sso.pojo.ro.JoinAccountRo;
import com.github.lotus.sso.pojo.ro.LoginRo;
import com.github.lotus.sso.pojo.ro.ConfirmQrcodeRo;
import com.github.lotus.ums.api.pojo.vo.GetLoginQrcodeVo;
import com.github.lotus.sso.service.AccountService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Api(tags = "sso::账号")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccountController {
    private final AccountService service;

    @ApiOperation(value = "获取 Token", notes = "免登陆")
    @PostMapping("/login/token")
    public Result<String> login(@RequestBody LoginRo ro) {
        return Result.success(service.login(ro));
    }

    @ApiOperation(value = "注册账号", notes = "免登陆")
    @PostMapping("/join")
    public Result<String> join(@RequestBody JoinAccountRo ro) {
        return Result.success(service.join(ro));
    }

    @ApiOperation(value = "获取 登陆二维码", notes = "免登陆")
    @PostMapping("/login/qrcode")
    public Result<GetLoginQrcodeVo> qrcode() {
        return Result.success(service.getLoginQrcode());
    }

    @ApiOperation(value = "获取 登陆二维码 确认")
    @PostMapping("/login/qrcode/confirm")
    public Result<Void> confirm(@Validated @RequestBody ConfirmQrcodeRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.confirmQrcode(ro);
        return Result.success();
    }

}
