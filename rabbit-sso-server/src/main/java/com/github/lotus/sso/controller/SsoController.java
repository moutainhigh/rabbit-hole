package com.github.lotus.sso.controller;

import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.ums.api.pojo.vo.GetLoginQrcodeVo;
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
import org.springframework.web.bind.annotation.PathVariable;
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
public class SsoController {
    private final SsoIndexService service;

    @ApiOperation("注册账号")
    @PostMapping("/join")
    @ResponseBody
    public Result<Void> createAccount(@Validated @ModelAttribute JoinRo ro) {
        service.createAccount(ro);
        return Result.success();
    }

    @ApiOperation(value = "微信公众号二维码", notes = "免登陆")
    @GetMapping("/wx/{appid}/qrcode")
    @ResponseBody
    public Result<WxMpQrCodeVo> getWxQrCode(@PathVariable("appid") String appid) {
        return Result.success(service.getWxQrCode(appid));
    }

    @ApiOperation(value = "获取微信登陆状态", notes = "免登陆")
    @GetMapping("/wx/login-status")
    @ResponseBody
    public Result<WxLoginStatusVo> getWxLoginStatus(@RequestParam("idFlag") String idFlag,
                                                    @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
        return Result.success(service.getWxLoginStatus(idFlag, redirectUrl));
    }

}
