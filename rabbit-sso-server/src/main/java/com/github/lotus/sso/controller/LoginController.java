package com.github.lotus.sso.controller;

import com.github.lotus.sso.pojo.ro.LoginUsePhoneRo;
import com.github.lotus.sso.pojo.ro.LoginUseUsernameRo;
import com.github.lotus.sso.service.SsoIndexService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Api(tags = "sso::登陆")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LoginController {
    private final SsoIndexService indexService;

    @ApiOperation("使用账户/密码进行登陆")
    @PostMapping("/username")
    @ResponseBody
    public Result<String> loginUseUsername(@Validated @RequestBody LoginUseUsernameRo ro) {
        return Result.success(indexService.loginUseUsername(ro));
    }

    @ApiOperation("使用手机号码进行登陆")
    @PostMapping("/phone")
    @ResponseBody
    public Result<String> loginUsePhone(@Validated @RequestBody LoginUsePhoneRo ro) {
        return Result.success(indexService.loginUsePhone(ro));
    }
}
