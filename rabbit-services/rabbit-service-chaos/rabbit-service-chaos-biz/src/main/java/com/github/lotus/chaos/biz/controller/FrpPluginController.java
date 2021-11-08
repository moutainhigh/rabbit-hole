package com.github.lotus.chaos.biz.controller;

import com.github.lotus.chaos.biz.service.FrpPluginService;
import com.github.lotus.chaos.biz.support.frp.ro.*;
import com.github.lotus.chaos.biz.support.frp.vo.FrpResult;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
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
@RequestMapping("/frp/plugin/opt")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FrpPluginController {
    private final FrpPluginService service;

    @UseLogger
    @ApiOperation("Login 指令")
    @PostMapping("/login")
    public FrpResult<?> login(@RequestBody FrpOptRo<LoginFrpRo> ro) {
        return service.login(ro.getContent());
    }

    @UseLogger
    @ApiOperation("NewProxy 指令")
    @PostMapping("/new-proxy")
    public FrpResult<?> newProxy(@RequestBody FrpOptRo<NewProxyFrpRo> ro) {
        return service.newProxy(ro.getContent());
    }

    @UseLogger
    @ApiOperation("Ping 指令")
    @PostMapping("/ping")
    public FrpResult<?> ping(@RequestBody FrpOptRo<PingFrpRo> ro) {
        return service.ping(ro.getContent());
    }

    @UseLogger
    @ApiOperation("NewWorkConn 指令")
    @PostMapping("/new-work-conn")
    public FrpResult<?> newWorkConn(@RequestBody FrpOptRo<NewWorkConnFrpRo> ro) {
        return service.newWorkConn(ro.getContent());
    }

    @UseLogger
    @ApiOperation("NewUserConn 指令")
    @PostMapping("/new-user-conn")
    public FrpResult<?> newUserConn(@RequestBody FrpOptRo<NewUserConnFrpRo> ro) {
        return service.newUserConn(ro.getContent());
    }
}
