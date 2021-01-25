package com.github.lotus.com.biz.controller;


import com.github.lotus.com.biz.service.DataDictService;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [基础模块] 数据字典表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Api(tags = "com::数据字典")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/data-dict")
public class DataDictController {
    private final DataDictService service;

    @ApiOperation("查询 - 数据字典项")
    @GetMapping("/{code}")
    public Result<List<KeyValue>> listKeyValueByCodeAndEnabledIsOn(@ApiParam(value = "数据字典类型", required = true) @PathVariable("code") String code) {
        return Result.success(service.listKeyValueByCodeAndEnabledIsOn(code));
    }

    @ApiOperation("查询 - 数据字典项(所有)")
    @GetMapping("/{code}/all")
    public Result<List<KeyValue>> selectListDictItemByCode(@ApiParam(value = "数据字典类型", required = true) @PathVariable("code") String code) {
        return Result.success(service.listKeyValueByCode(code));
    }

}

