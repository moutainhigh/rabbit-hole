package com.github.lotus.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.lotus.mall.biz.entity.Coupon;
import com.github.lotus.mall.biz.mapper.CouponMapper;
import com.github.lotus.mall.biz.mapstruct.CouponMapping;
import com.github.lotus.mall.biz.pojo.ro.CouponPagingRo;
import com.github.lotus.mall.biz.pojo.ro.CouponSaveRo;
import com.github.lotus.mall.biz.pojo.ro.GiveCouponRo;
import com.github.lotus.mall.biz.pojo.vo.CouponComplexVo;
import com.github.lotus.mall.biz.service.CouponService;
import com.github.lotus.mall.biz.service.UserCouponService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private final CouponMapping mapping;
    private final UserCouponService userCouponService;

    @Override
    public IPage<CouponComplexVo> paging(CouponPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertComplex);
    }

    @Override
    public void insertOne(CouponSaveRo ro) {
        this.saveOne(null, ro);
    }

    @Override
    public CouponComplexVo getComplex(Long id) {
        return this.convertComplex(getById(id));
    }

    @Override
    public void giveToUsers(Long id, GiveCouponRo ro) {
        userCouponService.giveToUsers(id, ro);
    }

    @Override
    public void revoke(Long id) {
        userCouponService.revokeByCouponId(id);
    }

    private void saveOne(Long id, CouponSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Coupon entity = mapping.asCoupon(ro);
        entity.setId(id);

        if (Objects.isNull(id)) {
            entity.setCreatedAt(now);
        } else {
            entity.setLastUpdatedAt(now);
        }
        this.validInsertOrUpdate(entity);
    }

    private CouponComplexVo convertComplex(Coupon entity) {
        return mapping.asCouponComplexVo(entity);
    }
}
