package in.hocg.rabbit.mall.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [店铺模块] 店铺表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2022-01-11
 */
@Api(tags = "mall::店铺")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/shop")
public class ShopController {
    private final ShopService service;

    @UseLogger
    @PostMapping
    @ApiOperation("新增 - 店铺")
    public Result<Void> insertOne(@Validated(Insert.class) @RequestBody ShopSaveRo ro) {
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger
    @PutMapping("/{id}")
    @ApiOperation("更新 - 店铺")
    public Result<Void> updateOne(@PathVariable("id") Long id, @Validated(Update.class) @RequestBody ShopSaveRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger
    @DeleteMapping
    @ApiOperation("删除 - 店铺")
    public Result<Void> delete(@Validated @RequestBody DeleteRo ro) {
        service.delete(ro);
        return Result.success();
    }

    @UseLogger
    @GetMapping("/{id}/complex")
    @ApiOperation("查看信息 - 店铺")
    public Result<ShopComplexVo> getComplex(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger
    @PostMapping("/_paging")
    @ApiOperation("分页查询 - 店铺")
    public Result<IPage<ShopOrdinaryVo>> paging(@Validated @RequestBody ShopPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger
    @PostMapping("/_complete")
    @ApiOperation("查询列表 - 店铺")
    public Result<List<ShopOrdinaryVo>> complete(@Validated @RequestBody ShopCompleteRo ro) {
        return Result.success(service.complete(ro));
    }
}

