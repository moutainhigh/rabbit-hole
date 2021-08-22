package com.github.lotus.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.Coupon;
import com.github.lotus.mall.biz.mapper.CouponMapper;
import com.github.lotus.mall.biz.pojo.ro.CouponPagingRo;
import com.github.lotus.mall.biz.pojo.ro.CouponSaveRo;
import com.github.lotus.mall.biz.pojo.ro.GiveCouponRo;
import com.github.lotus.mall.biz.pojo.vo.CouponComplexVo;
import com.github.lotus.mall.biz.service.CouponService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [促销模块] 优惠券表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CouponServiceImpl extends AbstractServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Override
    public IPage<CouponComplexVo> paging(CouponPagingRo ro) {
        return null;
    }

    @Override
    public void insertOne(CouponSaveRo ro) {

    }

    @Override
    public CouponComplexVo getComplex(Long id) {
        return null;
    }

    @Override
    public void giveToUsers(Long id, GiveCouponRo ro) {

    }

    @Override
    public void revoke(Long id) {

    }
}
