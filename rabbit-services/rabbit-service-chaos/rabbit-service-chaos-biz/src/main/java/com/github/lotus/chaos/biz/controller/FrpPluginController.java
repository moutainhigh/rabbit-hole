package com.github.lotus.chaos.biz.controller;

import com.github.lotus.chaos.biz.service.FrpPluginService;
import com.github.lotus.chaos.biz.support.frp.ro.*;
import com.github.lotus.chaos.biz.support.frp.vo.FrpResult;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.autoconfiguration.utils.web.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RestController
@Api(tags = "chaos::frp 插件")
@RequestMapping("/frp/opt")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FrpPluginController {
    private final FrpPluginService service;

    @UseLogger
    @ApiOperation("登陆(Login)")
    @PostMapping("/login")
    public FrpResult<?> login(@RequestBody FrpOptRo<LoginFrpRo> ro) {
        return service.login(ro.getContent());
    }

    @UseLogger
    @ApiOperation("创建代理(NewProxy)")
    @PostMapping("/new-proxy")
    public FrpResult<?> newProxy(@RequestBody String ro) {
        System.out.println("NewProxy " + ro);
        return service.newProxy(null);
    }

    @UseLogger
    @ApiOperation("心跳(Ping)")
    @PostMapping("/ping")
    public FrpResult<?> ping(@RequestBody FrpOptRo<PingFrpRo> ro) {
        return service.ping(ro.getContent());
    }

    @UseLogger
    @ApiOperation("创建工作连接(NewWorkConn)")
    @PostMapping("/new-work-conn")
    public FrpResult<?> newWorkConn(@RequestBody FrpOptRo<NewWorkConnFrpRo> ro) {
        return service.newWorkConn(ro.getContent());
    }

    @UseLogger
    @ApiOperation("创建用户连接(NewUserConn)")
    @PostMapping("/new-user-conn")
    public FrpResult<?> newUserConn(@RequestBody FrpOptRo<NewUserConnFrpRo> ro) {
        return service.newUserConn(ro.getContent());
    }

    @UseLogger
    @ApiOperation("配置(config)")
    @PostMapping("/config")
    public void config(@RequestBody String body) {
        log.debug("参数信息: {}", body);
    }
}
