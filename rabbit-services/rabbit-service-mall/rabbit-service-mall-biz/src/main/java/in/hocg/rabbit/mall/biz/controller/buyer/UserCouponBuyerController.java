package in.hocg.rabbit.mall.biz.controller.buyer;

import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mall.biz.pojo.ro.UserCouponBuyerScrollRo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponBuyerVo;
import in.hocg.rabbit.mall.biz.service.UserCouponService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2022/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = {"mall::用户优惠券", "买家"})
@RestController
@RequestMapping("/buyer/user-coupon")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserCouponBuyerController {
    private final UserCouponService service;

    @UseLogger
    @ApiOperation("滚动查询 - 用户优惠券")
    @PostMapping("/_scroll")
    public Result<IScroll<UserCouponBuyerVo>> scrollByBuyer(@Validated @RequestBody UserCouponBuyerScrollRo ro) {
        ro.setOwnerUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.scrollByBuyer(ro));
    }
}
