package com.github.lotus.docking.biz.controller;


import com.github.lotus.docking.biz.pojo.vo.WxMaLoginVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaPhoneNumberInfoVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaUserInfoVo;
import com.github.lotus.docking.biz.service.WxMaIndexService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-08-21
 */
@Slf4j
@Api(tags = {"docking::微信小程序"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/wx/miniapp/{appid}")
public class WxMaController {
    private final WxMaIndexService service;

    @ApiOperation("登陆接口")
    @PostMapping("/login")
    public Result<WxMaLoginVo> login(@PathVariable String appid, @RequestParam("code") String code,
                                     HttpServletRequest request) {
        return Result.success(service.login(appid, code));
    }

    @ApiOperation("获取用户信息接口")
    @GetMapping("/info")
    public Result<WxMaUserInfoVo> info(@PathVariable String appid, String sessionKey,
                                       String signature, String rawData, String encryptedData, String iv) {
        return Result.success(service.getUserInfo(appid, sessionKey, signature, rawData, encryptedData, iv));
    }

    @ApiOperation("获取用户绑定手机号信息")
    @GetMapping("/phone")
    public Result<WxMaPhoneNumberInfoVo> phone(@PathVariable String appid, String sessionKey,
                                               String signature, String rawData, String encryptedData, String iv) {
        return Result.success(service.getUserPhone(appid, sessionKey, signature, rawData, encryptedData, iv));
    }
}

