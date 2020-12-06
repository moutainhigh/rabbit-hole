package com.github.lotus.wl.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseCompleteRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseCreateRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseDeleteRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehousePagingRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseUpdateRo;
import com.github.lotus.wl.biz.pojo.vo.WarehouseComplexVo;
import com.github.lotus.wl.biz.service.WarehouseService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.exception.ServiceException;
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
 * [物流模块] 仓库表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Api(tags = "wl::物流仓库")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService service;

    @PostMapping
    @ApiOperation("创建 - 物流仓库")
    public Result create(@Validated @RequestBody WarehouseCreateRo ro) {
        Long userId = UserContextHolder.getUserId()
            .orElseThrow(() -> ServiceException.wrap("请先进行登陆"));
        ro.setCreator(userId);
        service.create(ro);
        return Result.success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新 - 物流仓库")
    public Result update(@PathVariable("id") Long id,
                         @Validated @RequestBody WarehouseUpdateRo ro) {
        Long userId = UserContextHolder.getUserId()
            .orElseThrow(() -> ServiceException.wrap("请先进行登陆"));
        ro.setUpdater(userId);
        service.update(id, ro);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("删除 - 物流仓库")
    public Result deletes(@Validated @RequestBody WarehouseDeleteRo ro) {
        service.delete(ro);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("详情 - 物流仓库")
    public WarehouseComplexVo query(@PathVariable("id") Long id) {
        return service.getWarehouse(id);
    }

    @PostMapping("/_paging")
    @ApiOperation("分页查询 - 物流仓库")
    public IPage<WarehouseComplexVo> paging(@Validated @RequestBody WarehousePagingRo ro) {
        return service.paging(ro);
    }


    @PostMapping("/_complete")
    @ApiOperation("检索 - 物流仓库")
    public List<WarehouseComplexVo> complete(@Validated @RequestBody WarehouseCompleteRo ro) {
        return service.complete(ro);
    }

}

