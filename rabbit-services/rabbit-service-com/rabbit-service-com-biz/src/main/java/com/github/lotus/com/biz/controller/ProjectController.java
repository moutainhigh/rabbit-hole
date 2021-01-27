package com.github.lotus.com.biz.controller;


import com.github.lotus.com.api.pojo.vo.ProjectComplexVo;
import com.github.lotus.com.biz.pojo.ro.ProjectCompleteRo;
import com.github.lotus.com.biz.service.ProjectService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [通用模块] 项目表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Api(tags = "com::项目")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService service;

    @PostMapping("/_complete")
    @ApiOperation("检索 - 项目")
    @ApiOperationSupport(author = "hocgin")
    public Result<List<ProjectComplexVo>> complete(@Validated @RequestBody ProjectCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @GetMapping("/{encoding:\\w+}")
    @ApiOperation("详情 - 项目")
    @ApiOperationSupport(author = "hocgin")
    public Result<ProjectComplexVo> getByEncoding(@PathVariable("encoding") String encoding) {
        return Result.success(service.getByEncoding(encoding));
    }
}

