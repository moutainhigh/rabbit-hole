package com.github.lotus.pay.biz.controller;


import com.github.lotus.pay.biz.pojo.ro.AccessPlatformInsertRo;
import com.github.lotus.pay.biz.service.AccessPlatformService;
import in.hocg.boot.web.result.Result;
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
 * [支付网关] 接入平台表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/access-platform")
public class AccessPlatformController {
    private final AccessPlatformService service;

    @ApiOperation("新增 - 第三方支付配置")
    @PostMapping
    public Result<Void> insert(@Validated @RequestBody AccessPlatformInsertRo ro) {
        service.insertOne(ro);
        return Result.success();
    }

}

