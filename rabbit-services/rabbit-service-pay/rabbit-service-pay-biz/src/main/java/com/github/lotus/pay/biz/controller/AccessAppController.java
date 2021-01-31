package com.github.lotus.pay.biz.controller;


import com.github.lotus.pay.biz.pojo.ro.AccessAppInsertRo;
import com.github.lotus.pay.biz.service.AccessAppService;
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
 * <p>
 * [支付网关] 接入应用表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Api(tags = {"pay::接入应用"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/access-app")
public class AccessAppController {
    private final AccessAppService service;

    @ApiOperation("创建接入应用")
    @PostMapping
    public Result<Void> insert(@Validated @RequestBody AccessAppInsertRo ro) {
        service.insertOne(ro);
        return Result.success();
    }
}

