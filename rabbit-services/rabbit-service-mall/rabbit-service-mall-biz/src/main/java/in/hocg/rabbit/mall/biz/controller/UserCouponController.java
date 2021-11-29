package in.hocg.rabbit.mall.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.biz.pojo.ro.UserCouponPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.rabbit.mall.biz.service.UserCouponService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
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
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/user-coupon")
public class UserCouponController {
    private final UserCouponService service;

    @UseLogger("分页查询 - 用户优惠券")
    @PostMapping("/_paging")
    public Result<IPage<UserCouponComplexVo>> paging(@Validated @RequestBody UserCouponPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger("撤回指定未使用优惠券 - 用户优惠券")
    @PostMapping("/{id:\\d+}/revoke")
    public Result<Void> revoke(@PathVariable Long id) {
        service.revokeById(id);
        return Result.success();
    }

}

