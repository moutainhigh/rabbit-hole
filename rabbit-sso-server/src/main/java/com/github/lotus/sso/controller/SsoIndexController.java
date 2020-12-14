package com.github.lotus.sso.controller;

import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.sso.pojo.ro.SendSmsCodeRo;
import com.github.lotus.sso.pojo.vo.WxLoginStatusVo;
import com.github.lotus.sso.service.SsoIndexService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "sso::通用")
@Controller
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SsoIndexController {
    private final SsoIndexService indexService;

    @ApiOperation("注册账号")
    @PostMapping("/join")
    @ResponseBody
    public Result<Void> createAccount(@Validated @ModelAttribute JoinRo ro) {
        indexService.createAccount(ro);
        return Result.success();
    }

    @ApiOperation("获取短信验证码")
    @PostMapping("/get-code")
    @ResponseBody
    public Result<Void> sendSmsCode(@Validated @ModelAttribute SendSmsCodeRo ro) {
        indexService.sendSmsCode(ro);
        return Result.success();
    }

    @ApiOperation("微信公众号二维码")
    @GetMapping("/wx/qrcode")
    @ResponseBody
    public Result<WxMpQrCodeVo> getWxQrCode() {
        return Result.success(indexService.getWxQrCode());
    }

    @ApiOperation("获取微信登陆状态")
    @GetMapping("/wx/login-status")
    @ResponseBody
    public Result<WxLoginStatusVo> getWxLoginStatus(@RequestParam("idFlag") String idFlag,
                                                    @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
        return Result.success(indexService.getWxLoginStatus(idFlag, redirectUrl));
    }
}
