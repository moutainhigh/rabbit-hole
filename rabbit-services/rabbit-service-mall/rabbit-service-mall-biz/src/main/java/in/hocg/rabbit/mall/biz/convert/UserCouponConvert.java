package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.Coupon;
import in.hocg.rabbit.mall.biz.entity.UserCoupon;
import in.hocg.rabbit.mall.biz.mapstruct.UserCouponMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.rabbit.mall.biz.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hocgin on 2022/1/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserCouponConvert {
    private final UserCouponMapping mapping;
    private final CouponService couponService;

    public UserCouponComplexVo asUserCouponComplexVo(UserCoupon entity) {
        UserCouponComplexVo result = mapping.asUserCouponComplexVo(entity);
        Long couponId = entity.getCouponId();
        Coupon coupon = couponService.getById(couponId);
        result.setTitle(coupon.getTitle());
        result.setUseInstructions(coupon.getUseInstructions());
        result.setCredit(coupon.getCredit());

        return result;
    }
}
