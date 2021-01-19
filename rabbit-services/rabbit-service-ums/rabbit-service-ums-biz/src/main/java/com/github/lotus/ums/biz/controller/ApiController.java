package com.github.lotus.ums.biz.controller;


import com.github.lotus.ums.biz.pojo.ro.SaveApiRo;
import com.github.lotus.ums.biz.service.ApiService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.validation.autoconfigure.group.Insert;
import in.hocg.boot.validation.autoconfigure.group.Update;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [权限模块] 接口表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/api")
public class ApiController {
    private final ApiService service;

    @ApiOperation("新增接口 - 接口")
    @PostMapping
    public Result<Long> insertOne(@Validated({Insert.class}) @RequestBody SaveApiRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.insertOne(ro));
    }

    @ApiOperation("修改接口 - 接口")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@ApiParam(value = "接口", required = true) @PathVariable Long id,
                                  @Validated({Update.class}) @RequestBody SaveApiRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }

    @ApiOperation("删除接口 - 接口")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@ApiParam(value = "接口", required = true) @PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }
}

