package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.api.enums.CouponUseStint;
import in.hocg.rabbit.mall.api.enums.UserCouponStatus;
import in.hocg.rabbit.mall.biz.entity.Coupon;
import in.hocg.rabbit.mall.biz.entity.UserCoupon;
import in.hocg.rabbit.mall.biz.mapper.UserCouponMapper;
import in.hocg.rabbit.mall.biz.mapstruct.UserCouponMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.GiveCouponRo;
import in.hocg.rabbit.mall.biz.pojo.ro.UserCouponPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.rabbit.mall.biz.service.CouponProductCategoryRefService;
import in.hocg.rabbit.mall.biz.service.CouponProductRefService;
import in.hocg.rabbit.mall.biz.service.CouponService;
import in.hocg.rabbit.mall.biz.service.UserCouponService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * [促销模块] 优惠券账号拥有人表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserCouponServiceImpl extends AbstractServiceImpl<UserCouponMapper, UserCoupon>
    implements UserCouponService {
    private final CouponService couponService;
    private final CouponProductCategoryRefService couponProductCategoryRefService;
    private final CouponProductRefService couponProductRefService;
    private final UserCouponMapping mapping;

    @Override
    public IPage<UserCouponComplexVo> paging(UserCouponPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertComplex);
    }

    @Override
    public void revokeById(Long userCouponId) {
        UserCoupon update = new UserCoupon();
        update.setStatus(UserCouponStatus.Cancelled.getCodeStr());
        update.setLastUpdatedAt(LocalDateTime.now());
        this.lambdaUpdate().eq(UserCoupon::getId, userCouponId)
            .eq(UserCoupon::getStatus, UserCouponStatus.UnUse.getCodeStr())
            .update(update);
    }

    @Override
    public void revokeByCouponId(Long couponId) {
        UserCoupon update = new UserCoupon();
        update.setStatus(UserCouponStatus.Cancelled.getCodeStr());
        update.setLastUpdatedAt(LocalDateTime.now());
        this.lambdaUpdate().eq(UserCoupon::getCouponId, couponId)
            .eq(UserCoupon::getStatus, UserCouponStatus.UnUse.getCodeStr())
            .update(update);
    }

    @Override
    public void giveToUsers(@NotNull Long couponId, @Validated GiveCouponRo ro) {
        Assert.notNull(couponService.getById(couponId), "优惠券模版错误");
        UserCoupon entity = mapping.asUserCoupon(ro);
        entity.setCouponId(couponId);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreator(ro.getUserId());
        entity.setStatus(UserCouponStatus.UnUse.getCodeStr());
        this.validInsert(entity);
    }

    @Override
    public List<UserCouponComplexVo> listComplexByUserId(Long userId) {
        return LangUtils.toList(this.listByUserId(userId), this::convertComplex);
    }

    @Override
    public boolean updateUsedStatus(Long userCouponId, BigDecimal usedAmt) {
        return lambdaUpdate().set(UserCoupon::getUsedAt, LocalDateTime.now())
            .set(UserCoupon::getStatus, UserCouponStatus.Used.getCodeStr())
            .set(UserCoupon::getUsedAmt, usedAmt)
            .eq(UserCoupon::getId, userCouponId)
            .eq(UserCoupon::getUserId, UserCouponStatus.UnUse.getCodeStr()).update();
    }

    @Override
    public void updateUnusedStatus(Long userCouponId) {
        final UserCoupon updated = new UserCoupon();
        updated.setId(userCouponId);
        updated.setStatus(UserCouponStatus.UnUse.getCodeStr());
        this.validUpdateById(updated);
    }

    private List<UserCoupon> listByUserId(Long userId) {
        return lambdaQuery().eq(UserCoupon::getUserId, userId).list();
    }

    private UserCouponComplexVo convertComplex(UserCoupon entity) {
        UserCouponComplexVo result = mapping.asUserCouponComplexVo(entity);
        Long couponId = entity.getCouponId();
        Coupon coupon = couponService.getById(couponId);
        String useStint = coupon.getUseStint();
        result.setTitle(coupon.getTitle());
        result.setUseInstructions(coupon.getUseInstructions());
        result.setUseStint(useStint);
        result.setUsePlatform(coupon.getUsePlatform());
        result.setCredit(coupon.getCredit());
        result.setMinPoint(coupon.getMinPoint());
        result.setCeiling(coupon.getCeiling());

        // 指定商品
        if (CouponUseStint.Product.eq(useStint)) {
            final List<ProductComplexVo> products = couponProductRefService.listComplexByCouponId(couponId);
            result.setCanUseProduct(products);
        }
        // 指定品类
        else if (CouponUseStint.Category.eq(useStint)) {
            final List<ProductCategoryComplexVo> productCategory = couponProductCategoryRefService.listComplexByCouponId(couponId);
            result.setCanUseProductCategory(productCategory);
        }
        // 通用类型
        else if (CouponUseStint.None.eq(useStint)) {
            log.debug("通用类型的优惠券");
        } else {
            throw ServiceException.wrap("优惠券使用类型异常");
        }
        return result;
    }
}
