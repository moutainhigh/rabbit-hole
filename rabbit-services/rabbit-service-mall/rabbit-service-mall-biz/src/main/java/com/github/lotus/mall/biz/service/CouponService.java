package com.github.lotus.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.Coupon;
import com.github.lotus.mall.biz.pojo.ro.CouponPagingRo;
import com.github.lotus.mall.biz.pojo.ro.CouponSaveRo;
import com.github.lotus.mall.biz.pojo.ro.GiveCouponRo;
import com.github.lotus.mall.biz.pojo.vo.CouponComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [促销模块] 优惠券表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface CouponService extends AbstractService<Coupon> {

    IPage<CouponComplexVo> paging(CouponPagingRo ro);

    void insertOne(CouponSaveRo ro);

    CouponComplexVo getComplex(Long id);

    void giveToUsers(Long id, GiveCouponRo ro);

    void revoke(Long id);
}
