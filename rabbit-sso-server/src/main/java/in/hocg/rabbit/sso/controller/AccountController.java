package in.hocg.rabbit.sso.controller;

import in.hocg.rabbit.sso.pojo.ro.JoinAccountRo;
import in.hocg.rabbit.sso.pojo.ro.LoginRo;
import in.hocg.rabbit.sso.pojo.ro.ConfirmQrcodeRo;
import in.hocg.rabbit.ums.api.pojo.vo.GetLoginQrcodeVo;
import in.hocg.rabbit.sso.service.AccountService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
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
