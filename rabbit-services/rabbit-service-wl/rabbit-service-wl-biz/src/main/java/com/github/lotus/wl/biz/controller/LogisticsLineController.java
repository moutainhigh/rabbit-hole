package com.github.lotus.wl.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLineBatchCreateRo;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLineCompleteRo;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLineCreateRo;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLinePagingRo;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLineSearchRo;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import com.github.lotus.wl.biz.pojo.vo.LogisticsLineComplexVo;
import com.github.lotus.wl.biz.pojo.vo.LogisticsLineSearchVo;
import com.github.lotus.wl.biz.service.LogisticsLineService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [物流模块] 物流线路表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Api(tags = "wl::物流线路")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/logistics-line")
public class LogisticsLineController {
    private final LogisticsLineService service;

    @PostMapping
    @ApiOperation("创建 - 物流线路")
    public Result<Void> create(@Validated @RequestBody LogisticsLineCreateRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setCreator(userId);
        service.create(ro);
        return Result.success();
    }

    @PostMapping("/batch")
    @ApiOperation("创建 - 物流线路")
    public Result<Void> batchCreate(@Validated @RequestBody LogisticsLineBatchCreateRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setCreator(userId);
        service.batchCreate(ro);
        return Result.success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新 - 物流线路")
    public Result<Void> update(@PathVariable("id") Long id,
                               @Validated @RequestBody LogisticsLineUpdateRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setUpdater(userId);
        service.update(id, ro);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除 - 物流线路")
    public Result<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("详情 - 物流线路")
    public Result<LogisticsLineComplexVo> query(@PathVariable("id") Long id) {
        return Result.success(service.getLogisticsLine(id));
    }

    @PostMapping("/_paging")
    @ApiOperation("分页查询 - 物流线路")
    public Result<IPage<LogisticsLineComplexVo>> paging(@Validated @RequestBody LogisticsLinePagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @PostMapping("/_complete")
    @ApiOperation("检索 - 物流线路")
    public Result<List<LogisticsLineComplexVo>> complete(@Validated @RequestBody LogisticsLineCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @PostMapping("/_search")
    @ApiOperation("线路搜索 - 物流线路")
    public Result<IPage<LogisticsLineSearchVo>> search(@Validated @RequestBody LogisticsLineSearchRo ro) {
        return Result.success(service.search(ro));
    }
}

