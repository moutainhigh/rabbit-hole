package in.hocg.rabbit.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.rabbit.mall.biz.convert.CouponConvert;
import in.hocg.rabbit.mall.biz.entity.Coupon;
import in.hocg.rabbit.mall.biz.mapper.CouponMapper;
import in.hocg.rabbit.mall.biz.mapstruct.CouponMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.CouponPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.CouponSaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.GiveCouponRo;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.CouponService;
import in.hocg.rabbit.mall.biz.service.CouponStintRuleRefService;
import in.hocg.rabbit.mall.biz.service.StintRuleService;
import in.hocg.rabbit.mall.biz.service.UserCouponService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
@UseConvert(CouponConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CouponServiceImpl extends AbstractServiceImpl<CouponMapper, Coupon> implements CouponService {
    private final CouponMapping mapping;
    private final CouponConvert convert;
    private final UserCouponService userCouponService;
    private final StintRuleService stintRuleService;
    private final CouponStintRuleRefService couponStintRuleRefService;

    @Override
    public IPage<CouponOrdinaryVo> paging(CouponPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(convert::asCouponOrdinaryVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(CouponSaveRo ro) {
        this.saveOne(null, ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, CouponSaveRo ro) {
        this.saveOne(id, ro);
    }

    @Override
    public CouponComplexVo getComplex(Long id) {
        return convert.convertComplex(getById(id));
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
        Coupon entity = mapping.asCoupon(ro);
        entity.setId(id);
        this.saveOrUpdate(entity);

        List<Long> stintRule = ro.getStintRule();
        if (Objects.nonNull(stintRule)) {
            couponStintRuleRefService.saveBatchByCouponId(id, stintRule);
        }
    }
}
