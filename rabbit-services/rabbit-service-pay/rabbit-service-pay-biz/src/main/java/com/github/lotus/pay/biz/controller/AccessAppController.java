package com.github.lotus.pay.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.pay.biz.pojo.ro.AccessAppCompleteRo;
import com.github.lotus.pay.biz.pojo.ro.AccessAppInsertRo;
import com.github.lotus.pay.biz.pojo.ro.AccessAppPagingRo;
import com.github.lotus.pay.biz.pojo.vo.AccessAppComplexVo;
import com.github.lotus.pay.biz.service.AccessAppService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import in.hocg.boot.web.servlet.SpringServletContext;
import in.hocg.boot.web.utils.web.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ApiOperation("新增 - 接入应用")
    @PostMapping
    public Result<Void> insert(@Validated @RequestBody AccessAppInsertRo ro) {
        ro.setClientIp(RequestUtils.getClientIp(SpringServletContext.getRequest().orElseThrow(UnsupportedOperationException::new)));
        service.insertOne(ro);
        return Result.success();
    }

    @ApiOperation("分页查询 - 接入应用")
    @PostMapping("/_paging")
    public Result<IPage<AccessAppComplexVo>> paging(@Validated @RequestBody AccessAppPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("查询详情 - 接入应用")
    @GetMapping("/{id}")
    public Result<AccessAppComplexVo> getComplex(@ApiParam(value = "接入应用", required = true) @PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @ApiOperation("检索 - 接入应用")
    @UseLogger("检索 - 接入应用")
    @PostMapping("/_complete")
    public Result<List<AccessAppComplexVo>> complete(@Validated @RequestBody AccessAppCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @ApiOperation("删除 - 接入应用")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@ApiParam(value = "接入应用", required = true) @PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }
}

