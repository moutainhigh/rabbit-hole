package in.hocg.rabbit.mall.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.biz.pojo.ro.UserCouponPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.UserCouponService;
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
@Api(tags = {"mall::用户优惠券", "mall"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/user-coupon")
public class UserCouponController {
    private final UserCouponService service;

    @UseLogger
    @ApiOperation("分页查询 - 用户优惠券")
    @PostMapping("/_paging")
    public Result<IPage<UserCouponOrdinaryVo>> paging(@Validated @RequestBody UserCouponPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger
    @GetMapping("/{id}/complex")
    @ApiOperation("查看信息 - 用户优惠券")
    public Result<UserCouponComplexVo> getComplexById(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger
    @ApiOperation("撤回指定未使用优惠券 - 用户优惠券")
    @PostMapping("/{id:\\d+}/revoke")
    public Result<Void> revoke(@PathVariable Long id) {
        service.revokeById(id);
        return Result.success();
    }

}

