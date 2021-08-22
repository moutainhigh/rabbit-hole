package com.github.lotus.mall.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.pojo.ro.ProductCompleteRo;
import com.github.lotus.mall.biz.pojo.ro.ProductSaveRo;
import com.github.lotus.mall.biz.pojo.ro.ProductPagingRo;
import com.github.lotus.mall.biz.pojo.vo.ProductComplexVo;
import com.github.lotus.mall.biz.service.ProductService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * [Shop模块] 商品表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-03-10
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    @UseLogger("新增 - 商品")
    @PostMapping
    public Result<Void> insertOne(@Validated @RequestBody ProductSaveRo ro) {
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger("更新 - 商品")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@PathVariable("id") Long id, @Validated @RequestBody ProductSaveRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger("删除 - 商品")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@PathVariable("id") Long id) {
        service.deleteOne(id);
        return Result.success();
    }

    @UseLogger("查看信息 - 商品")
    @GetMapping("/{id}")
    public Result<ProductComplexVo> getComplexById(@PathVariable("id") Long id) {
        return Result.success(service.getComplexById(id));
    }

    @UseLogger("分页查询 - 商品")
    @PostMapping("/_paging")
    public Result<IPage<ProductComplexVo>> paging(@Validated @RequestBody ProductPagingRo ro) {
        return Result.success(service.paging(ro));
    }


    @PostMapping("/_complete")
    @UseLogger("查询列表 - 商品")
    public Result<List<ProductComplexVo>> complete(@Validated @RequestBody ProductCompleteRo qo) {
        return Result.success(service.complete(qo));
    }
}

