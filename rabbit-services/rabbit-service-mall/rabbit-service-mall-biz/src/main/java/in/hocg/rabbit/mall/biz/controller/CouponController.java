package in.hocg.rabbit.mall.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.biz.pojo.ro.CouponPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.CouponSaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.GiveCouponRo;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponComplexVo;
import in.hocg.rabbit.mall.biz.service.CouponService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 优惠券表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-03-17
 */
@Api(tags = "mall::优惠券")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/coupon")
public class CouponController {
    private final CouponService service;

    @UseLogger
    @ApiOperation("分页查询 - 优惠券模版")
    @PostMapping("/_paging")
    public Result<IPage<CouponComplexVo>> paging(@Validated @RequestBody CouponPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger
    @ApiOperation("创建 - 优惠券模版")
    @PostMapping
    public Result<Void> insertOne(@Validated @RequestBody CouponSaveRo ro) {
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("查看详情 - 优惠券")
    @GetMapping("/{id:\\d+}/complex")
    public Result<CouponComplexVo> getComplex(@PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger
    @ApiOperation("赠送优惠券 - 优惠券")
    @PostMapping("/{id:\\d+}/give")
    public Result<Void> give(@PathVariable Long id, @Validated @RequestBody GiveCouponRo ro) {
        service.giveToUsers(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("撤回所有未使用优惠券 - 优惠券")
    @PostMapping("/{id:\\d+}/revoke")
    public Result<Void> revoke(@PathVariable Long id) {
        service.revoke(id);
        return Result.success();
    }
}

