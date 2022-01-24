package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.Coupon;
import in.hocg.rabbit.mall.biz.mapstruct.CouponMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponOrdinaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CouponConvert {
    private final CouponMapping mapping;

    public CouponComplexVo convertComplex(Coupon entity) {
        return mapping.asCouponComplexVo(entity);
    }

    public CouponOrdinaryVo asCouponOrdinaryVo(Coupon entity) {
        return mapping.asCouponOrdinaryVo(entity);
    }
}
