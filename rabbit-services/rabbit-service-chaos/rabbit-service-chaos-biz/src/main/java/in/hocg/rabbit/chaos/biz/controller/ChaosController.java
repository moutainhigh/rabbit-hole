package in.hocg.rabbit.chaos.biz.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import in.hocg.rabbit.chaos.biz.constant.SentinelKeys;
import in.hocg.rabbit.chaos.biz.pojo.ro.SendEmailCodeRo;
import in.hocg.rabbit.chaos.biz.pojo.ro.SendSmsCodeRo;
import in.hocg.rabbit.chaos.biz.pojo.vo.IpAddressVo;
import in.hocg.rabbit.chaos.biz.service.ChaosService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import in.hocg.boot.web.autoconfiguration.utils.web.RequestUtils;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Api(tags = "chaos::通用")
@Controller
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ChaosController {
    private final ChaosService service;

    @ApiOperation("发送短信验证码")
    @PostMapping("/sms-code")
    @ResponseBody
    @SentinelResource(SentinelKeys.GET_SMS_CODE)
    public Result<Long> sendSmsCode(@Validated @RequestBody SendSmsCodeRo qo) {
        return Result.success(service.sendSmsCode(qo));
    }

    @ApiOperation("发送邮箱验证码")
    @PostMapping("/email-code")
    @ResponseBody
    public Result<Void> sendEmailCode(@Validated @RequestBody SendEmailCodeRo ro) {
        service.sendEmailCode(ro);
        return Result.success();
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
    public Result<IpAddressVo> getCurrentAddress(HttpServletRequest request) {
        String clientIp = RequestUtils.getClientIp(request);
        return Result.success(service.getAddress4ip(clientIp));
    }

    @ApiOperation("获取数据加密后的值")
    @GetMapping("/encrypt")
    @ResponseBody
    public Result<String> encrypt(@RequestParam(name = "data") String data) {
        return Result.success(service.encrypt(data));
    }

    @ApiOperation("文件下载")
    @GetMapping("/download")
    public ResponseEntity<?> download(@RequestParam("url") String url,
                                      @RequestParam(value = "filename", required = false) String filename) {
        return ResponseUtils.download(url, filename);
    }
}