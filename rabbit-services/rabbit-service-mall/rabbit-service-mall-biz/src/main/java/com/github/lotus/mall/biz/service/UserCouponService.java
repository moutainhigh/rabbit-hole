package com.github.lotus.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.UserCoupon;
import com.github.lotus.mall.biz.pojo.ro.GiveCouponRo;
import com.github.lotus.mall.biz.pojo.ro.UserCouponPagingRo;
import com.github.lotus.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

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

    IPage<UserCouponComplexVo> paging(UserCouponPagingRo ro);

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

    List<UserCouponComplexVo> listComplexByUserId(Long userId);

    /**
     * 更新用户优惠券为已使用
     *
     * @param userCouponId
     * @param usedAmt
     * @return
     */
    boolean updateUsedStatus(Long userCouponId, BigDecimal usedAmt);

    void updateUnusedStatus(Long userCouponId);
}
