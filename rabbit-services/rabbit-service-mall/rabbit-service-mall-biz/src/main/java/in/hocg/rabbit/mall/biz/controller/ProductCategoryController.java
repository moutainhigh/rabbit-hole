package in.hocg.rabbit.mall.biz.controller;


import in.hocg.rabbit.mall.biz.pojo.ro.ProductCategorySaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCategoryTreeRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryTreeVo;
import in.hocg.rabbit.mall.biz.service.ProductCategoryService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "mall::商品品类")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/product-category")
public class ProductCategoryController {
    private final ProductCategoryService service;

    @UseLogger
    @ApiOperation("新增 - 商品品类")
    @PostMapping
    public Result<Void> insertOne(@Validated @RequestBody ProductCategorySaveRo ro) {
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("获取 - 商品品类树")
    @PostMapping("/tree")
    public Result<List<ProductCategoryTreeVo>> listTree(@Validated @RequestBody ProductCategoryTreeRo ro) {
        return Result.success(service.listTree(ro));
    }

    @UseLogger
    @ApiOperation("更新 - 商品品类")
    @PutMapping("/{id:\\d+}")
    public Result<Void> updateOne(@PathVariable("id") Long id, @Validated @RequestBody ProductCategorySaveRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("查看 - 商品品类详情")
    @GetMapping("/{id:\\d+}")
    public Result<ProductCategoryComplexVo> getComplex(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger
    @ApiOperation("删除 - 商品品类及其子品类")
    @DeleteMapping("/{id:\\d+}")
    public Result<Void> deleteAll(@PathVariable("id") Long id) {
        service.deleteAllById(id);
        return Result.success();
    }

}

