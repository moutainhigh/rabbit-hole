package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.UserCoupon;
import in.hocg.rabbit.mall.biz.pojo.ro.GiveCouponRo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponBuyerVo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponComplexVo;
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
    @Mapping(target = "encoding", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "usedAt", ignore = true)
    @Mapping(target = "usedAmt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expiredFlag", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "couponId", ignore = true)
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

    UserCouponBuyerVo asUserCouponBuyerVo(UserCoupon entity);
}
