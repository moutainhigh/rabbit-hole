package in.hocg.rabbit.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.mall.biz.entity.UserCoupon;
import in.hocg.rabbit.mall.biz.pojo.ro.GiveCouponRo;
import in.hocg.rabbit.mall.biz.pojo.ro.UserCouponBuyerScrollRo;
import in.hocg.rabbit.mall.biz.pojo.ro.UserCouponPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponBuyerVo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponOrdinaryVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * [促销模块] 优惠券账号拥有人表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface UserCouponService extends AbstractService<UserCoupon> {

    IPage<UserCouponOrdinaryVo> paging(UserCouponPagingRo ro);

    /**
     * 撤销优惠券
     *
     * @param userCouponId 用户优惠券ID
     */
    void revokeById(Long userCouponId);

    /**
     * 撤回优惠券
     *
     * @param couponId 优惠券模版ID
     */
    void revokeByCouponId(Long couponId);

    /**
     * 赠送优惠券
     *
     * @param couponId 优惠券模版ID
     * @param ro       优惠券信息
     */
    void giveToUsers(Long couponId, GiveCouponRo ro);

    boolean updateUnusedStatus(List<Long> userCouponId);

    List<UserCoupon> listByAvailableAndOwnerUserId(Long ownerUserId);

    boolean updateUsedStatus(List<UserCoupon> update);

    IScroll<UserCouponBuyerVo> scrollByBuyer(UserCouponBuyerScrollRo ro);

    UserCouponComplexVo getComplex(Long id);
}
