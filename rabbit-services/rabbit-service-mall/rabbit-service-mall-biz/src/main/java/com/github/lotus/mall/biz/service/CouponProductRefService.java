package com.github.lotus.mall.biz.service;

import com.github.lotus.mall.biz.entity.CouponProductRef;
import com.github.lotus.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [促销模块] 优惠券可用商品表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface CouponProductRefService extends AbstractService<CouponProductRef> {

    List<ProductComplexVo> listComplexByCouponId(Long couponId);
}
