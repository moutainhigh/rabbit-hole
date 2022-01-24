package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.Coupon;
import in.hocg.rabbit.mall.biz.pojo.ro.CouponSaveRo;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponOrdinaryVo;
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
    CouponComplexVo asCouponComplexVo(Coupon entity);

    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Coupon asCoupon(CouponSaveRo ro);

    CouponOrdinaryVo asCouponOrdinaryVo(Coupon entity);
}
