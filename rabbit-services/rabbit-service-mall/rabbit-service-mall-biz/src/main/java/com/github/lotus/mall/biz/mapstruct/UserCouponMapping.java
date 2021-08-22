package com.github.lotus.mall.biz.mapstruct;

import com.github.lotus.mall.biz.entity.UserCoupon;
import com.github.lotus.mall.biz.pojo.ro.GiveCouponRo;
import com.github.lotus.mall.biz.pojo.vo.UserCouponComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserCouponMapping {
    @Mapping(target = "usedAt", ignore = true)
    @Mapping(target = "usedAmt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expiredFlag", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "couponNo", ignore = true)
    @Mapping(target = "couponId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    UserCoupon asUserCoupon(GiveCouponRo ro);

    @Mapping(target = "couponType", ignore = true)
    @Mapping(target = "useStint", ignore = true)
    @Mapping(target = "usePlatform", ignore = true)
    @Mapping(target = "useInstructions", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "minPoint", ignore = true)
    @Mapping(target = "credit", ignore = true)
    @Mapping(target = "ceiling", ignore = true)
    @Mapping(target = "canUseProductCategory", ignore = true)
    @Mapping(target = "canUseProduct", ignore = true)
    UserCouponComplexVo asUserCouponComplexVo(UserCoupon entity);
}
