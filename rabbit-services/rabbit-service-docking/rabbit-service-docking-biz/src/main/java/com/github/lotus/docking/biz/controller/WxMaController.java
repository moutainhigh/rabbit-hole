package com.github.lotus.docking.biz.controller;


import com.github.lotus.docking.biz.pojo.ro.GetMaUserTokenRo;
import com.github.lotus.docking.biz.pojo.vo.WxMaLoginVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaPhoneNumberInfoVo;
import com.github.lotus.docking.biz.service.WxMaIndexService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("登陆/注册接口")
    @PostMapping("/token")
    public Result<WxMaLoginVo> getUserToken(@PathVariable String appid, @Validated @RequestBody GetMaUserTokenRo ro) {
        return Result.success(service.getUserToken(appid, ro));
    }

    @ApiOperation("获取用户绑定手机号信息")
    @GetMapping("/phone")
    public Result<WxMaPhoneNumberInfoVo> phone(@PathVariable String appid, String sessionKey,
                                               String signature, String rawData, String encryptedData, String iv) {
        return Result.success(service.getUserPhone(appid, sessionKey, signature, rawData, encryptedData, iv));
    }
}

