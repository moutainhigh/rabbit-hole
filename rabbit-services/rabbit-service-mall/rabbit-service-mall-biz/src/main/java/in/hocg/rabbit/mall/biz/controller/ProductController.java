package in.hocg.rabbit.mall.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductSaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.service.ProductService;
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
 * [Shop模块] 商品表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-03-10
 */
@Api(tags = "mall::商品")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    @UseLogger
    @PostMapping
    @ApiOperation("新增 - 商品")
    public Result<Void> insertOne(@Validated @RequestBody ProductSaveRo ro) {
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger
    @PutMapping("/{id}")
    @ApiOperation("更新 - 商品")
    public Result<Void> updateOne(@PathVariable("id") Long id, @Validated @RequestBody ProductSaveRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger
    @DeleteMapping("/{id}")
    @ApiOperation("删除 - 商品")
    public Result<Void> deleteOne(@PathVariable("id") Long id) {
        service.deleteOne(id);
        return Result.success();
    }

    @UseLogger
    @GetMapping("/{id}/complex")
    @ApiOperation("查看信息 - 商品")
    public Result<ProductComplexVo> getComplexById(@PathVariable("id") Long id) {
        return Result.success(service.getComplexById(id));
    }

    @UseLogger
    @PostMapping("/_paging")
    @ApiOperation("分页查询 - 商品")
    public Result<IPage<ProductComplexVo>> paging(@Validated @RequestBody ProductPagingRo ro) {
        return Result.success(service.paging(ro));
    }


    @UseLogger
    @PostMapping("/_complete")
    @ApiOperation("查询列表 - 商品")
    public Result<List<ProductComplexVo>> complete(@Validated @RequestBody ProductCompleteRo qo) {
        return Result.success(service.complete(qo));
    }
}

