package com.github.lotus.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.UserCoupon;
import com.github.lotus.mall.biz.pojo.ro.UserCouponPagingRo;
import com.github.lotus.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

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

    void revokeById(Long id);
}
