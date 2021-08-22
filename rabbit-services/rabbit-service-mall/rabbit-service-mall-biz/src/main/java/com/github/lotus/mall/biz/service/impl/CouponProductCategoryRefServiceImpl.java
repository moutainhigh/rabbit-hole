package com.github.lotus.mall.biz.service.impl;

import com.github.lotus.mall.biz.entity.CouponProductCategoryRef;
import com.github.lotus.mall.biz.entity.CouponProductRef;
import com.github.lotus.mall.biz.entity.ProductCategory;
import com.github.lotus.mall.biz.mapper.CouponProductCategoryRefMapper;
import com.github.lotus.mall.biz.pojo.vo.ProductCategoryComplexVo;
import com.github.lotus.mall.biz.service.CouponProductCategoryRefService;
import com.github.lotus.mall.biz.service.ProductCategoryService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [促销模块] 优惠券可用商品品类表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CouponProductCategoryRefServiceImpl extends AbstractServiceImpl<CouponProductCategoryRefMapper, CouponProductCategoryRef> implements CouponProductCategoryRefService {
    private final ProductCategoryService productCategoryService;

    @Override
    public List<ProductCategoryComplexVo> listComplexByCouponId(Long couponId) {
        return LangUtils.toList(this.listProductCategoryByCouponId(couponId), productCategoryService::convertComplex);
    }

    public List<ProductCategory> listProductCategoryByCouponId(Long couponId) {
        return baseMapper.listProductCategoryByCouponId(couponId);
    }
}
