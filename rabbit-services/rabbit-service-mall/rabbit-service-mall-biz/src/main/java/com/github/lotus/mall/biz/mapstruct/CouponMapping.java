package com.github.lotus.mall.biz.mapstruct;

import com.github.lotus.mall.biz.entity.Coupon;
import com.github.lotus.mall.biz.pojo.ro.CouponSaveRo;
import com.github.lotus.mall.biz.pojo.vo.CouponComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface CouponMapping {
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "canUseProductCategory", ignore = true)
    @Mapping(target = "canUseProduct", ignore = true)
    CouponComplexVo asCouponComplexVo(Coupon entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Coupon asCoupon(CouponSaveRo ro);
}
