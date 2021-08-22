package com.github.lotus.mall.biz.controller;


import com.github.lotus.mall.biz.pojo.ro.ProductCategorySaveRo;
import com.github.lotus.mall.biz.pojo.ro.ProductCategoryTreeRo;
import com.github.lotus.mall.biz.pojo.ro.ProductCategoryUpdateRo;
import com.github.lotus.mall.biz.pojo.vo.ProductCategoryComplexVo;
import com.github.lotus.mall.biz.pojo.vo.ProductCategoryTreeVo;
import com.github.lotus.mall.biz.service.ProductCategoryService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * [Shop模块] 商品品类表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-03-14
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/product-category")
public class ProductCategoryController {
    private final ProductCategoryService service;

    @UseLogger("新增 - 商品品类")
    @PostMapping
    public Result<Void> insertOne(@Validated @RequestBody ProductCategorySaveRo ro) {
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger("获取 - 商品品类树")
    @PostMapping("/tree")
    public Result<List<ProductCategoryTreeVo>> listTree(@Validated @RequestBody ProductCategoryTreeRo ro) {
        return Result.success(service.listTree(ro));
    }

    @UseLogger("更新 - 商品品类")
    @PutMapping("/{id:\\d+}")
    public Result<Void> updateOne(@PathVariable("id") Long id, @Validated @RequestBody ProductCategorySaveRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger("查看 - 商品品类详情")
    @GetMapping("/{id:\\d+}/complex")
    public Result<ProductCategoryComplexVo> getComplex(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger("删除 - 商品品类及其子品类")
    @DeleteMapping("/{id:\\d+}")
    public Result<Void> deleteAll(@PathVariable("id") Long id) {
        service.deleteAllById(id);
        return Result.success();
    }

}
