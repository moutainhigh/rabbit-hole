package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.UserCoupon;
import in.hocg.rabbit.mall.biz.pojo.ro.GiveCouponRo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponBuyerVo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponOrdinaryVo;
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
    @Mapping(target = "startAt", ignore = true)
    @Mapping(target = "endAt", ignore = true)
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

    @Mapping(target = "statusName", ignore = true)
    @Mapping(target = "ownerUserName", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "coupon", ignore = true)
    UserCouponComplexVo asUserCouponComplexVo(UserCoupon entity);

    UserCouponBuyerVo asUserCouponBuyerVo(UserCoupon entity);

    @Mapping(target = "statusName", ignore = true)
    @Mapping(target = "ownerUserName", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    UserCouponOrdinaryVo asUserCouponOrdinaryVo(UserCoupon entity);
}
