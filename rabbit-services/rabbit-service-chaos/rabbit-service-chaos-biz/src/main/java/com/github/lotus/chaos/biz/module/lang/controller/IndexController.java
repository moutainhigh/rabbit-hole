package com.github.lotus.chaos.biz.module.lang.controller;

import com.github.lotus.chaos.biz.module.lang.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.biz.module.lang.pojo.vo.IpAddressVo;
import com.github.lotus.chaos.biz.module.lang.service.IndexService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import in.hocg.boot.web.utils.web.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Api(tags = "通用")
@Controller
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndexController {
    private final IndexService service;

    @ApiOperation("发送短信验证码")
    @PostMapping("/sms-code")
    @ResponseBody
    public void sendSmsCode(@Validated @RequestBody SendSmsCodeRo qo) {
        service.sendSmsCode(qo);
    }

    @ApiOperation("获取图形验证码")
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(5, request, response);
        CaptchaUtil.out(130, 48, 5, request, response);
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);
        CaptchaUtil.out(gifCaptcha, request, response);
    }

    @ApiOperation("获取当前用户的位置")
    @GetMapping("/address4ip")
    @ResponseBody
    public IpAddressVo getCurrentAddress(HttpServletRequest request) {
        String clientIp = RequestUtils.getClientIp(request);
        return service.getAddress4ip(getTestAddress(clientIp));
    }

    private String getTestAddress(String ip) {
        if (Arrays.asList(new String[]{
            "0:0:0:0:0:0:0:1",
            "127.0.0.1"
        }).contains(ip)) {
            return "110.80.68.212";
        }
        return ip;
    }
}
