package com.github.lotus.mall.biz.service.impl;

import com.github.lotus.mall.biz.entity.CouponProductRef;
import com.github.lotus.mall.biz.mapper.CouponProductRefMapper;
import com.github.lotus.mall.biz.pojo.vo.ProductComplexVo;
import com.github.lotus.mall.biz.service.CouponProductRefService;
import com.github.lotus.mall.biz.service.ProductService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [促销模块] 优惠券可用商品表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CouponProductRefServiceImpl extends AbstractServiceImpl<CouponProductRefMapper, CouponProductRef> implements CouponProductRefService {
    private final ProductService productService;
    @Override
    public List<ProductComplexVo> listComplexByCouponId(Long couponId) {
        return LangUtils.toList(baseMapper.listProductCategoryByCouponId(couponId), productService::convertComplex);
    }

}
