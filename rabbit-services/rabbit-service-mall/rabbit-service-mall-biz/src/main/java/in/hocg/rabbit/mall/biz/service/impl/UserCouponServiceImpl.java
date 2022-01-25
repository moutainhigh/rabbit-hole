package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.utils.PageUtils;
import in.hocg.rabbit.com.api.SnCodeServiceApi;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.mall.api.enums.coupon.UserCouponStatus;
import in.hocg.rabbit.mall.biz.convert.UserCouponConvert;
import in.hocg.rabbit.mall.biz.entity.Coupon;
import in.hocg.rabbit.mall.biz.entity.UserCoupon;
import in.hocg.rabbit.mall.biz.mapper.UserCouponMapper;
import in.hocg.rabbit.mall.biz.mapstruct.UserCouponMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.GiveCouponRo;
import in.hocg.rabbit.mall.biz.pojo.ro.UserCouponBuyerScrollRo;
import in.hocg.rabbit.mall.biz.pojo.ro.UserCouponPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.*;
import in.hocg.rabbit.mall.biz.service.CouponService;
import in.hocg.rabbit.mall.biz.service.UserCouponService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * [促销模块] 优惠券账号拥有人表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@UseConvert(UserCouponConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserCouponServiceImpl extends AbstractServiceImpl<UserCouponMapper, UserCoupon>
    implements UserCouponService {
    private final UserCouponConvert convert;
    private final CouponService couponService;
    private final UserCouponMapping mapping;
    private final SnCodeServiceApi codeServiceApi;

    @Override
    public IPage<UserCouponOrdinaryVo> paging(UserCouponPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(convert::asUserCouponOrdinaryVo);
    }

    @Override
    public UserCouponComplexVo getComplex(Long id) {
        return convert.asUserCouponComplexVo(getById(id));
    }

    @Override
    public void revokeById(Long userCouponId) {
        UserCoupon update = new UserCoupon();
        update.setStatus(UserCouponStatus.Cancelled.getCodeStr());
        this.lambdaUpdate().eq(UserCoupon::getId, userCouponId)
            .eq(UserCoupon::getStatus, UserCouponStatus.UnUse.getCodeStr())
            .update(update);
    }

    @Override
    public void revokeByCouponId(Long couponId) {
        UserCoupon update = new UserCoupon();
        update.setStatus(UserCouponStatus.Cancelled.getCodeStr());
        this.lambdaUpdate().eq(UserCoupon::getCouponId, couponId)
            .eq(UserCoupon::getStatus, UserCouponStatus.UnUse.getCodeStr())
            .update(update);
    }

    @Override
    public void giveToUsers(@NotNull Long couponId, @Validated GiveCouponRo ro) {
        Assert.notNull(couponService.getById(couponId), "优惠券模版错误");
        UserCoupon entity = mapping.asUserCoupon(ro);
        entity.setEncoding(codeServiceApi.getSnCode(CodeType.UserCoupon.getCodeStr()));
        entity.setCouponId(couponId);
        entity.setStatus(UserCouponStatus.UnUse.getCodeStr());
        this.validInsert(entity);
    }

    @Override
    public boolean updateUnusedStatus(List<Long> userCouponIds) {
        for (Long userCouponId : userCouponIds) {
            boolean isOk = lambdaUpdate().set(UserCoupon::getStatus, UserCouponStatus.UnUse.getCodeStr())
                .set(UserCoupon::getUsedAmt, null)
                .set(UserCoupon::getUsedAt, null)
                .eq(UserCoupon::getId, userCouponId)
                .eq(UserCoupon::getStatus, UserCouponStatus.UnUse.getCodeStr()).update();
            Assert.isTrue(isOk, "更新状态失败");
        }
        return true;
    }

    @Override
    public List<UserCoupon> listByAvailableAndOwnerUserId(Long ownerUserId) {
        return lambdaQuery().eq(UserCoupon::getOwnerUserId, ownerUserId)
            .eq(UserCoupon::getExpiredFlag, false)
            .eq(UserCoupon::getStatus, UserCouponStatus.UnUse.getCodeStr())
            .list();
    }

    @Override
    public boolean updateUsedStatus(List<UserCoupon> updates) {
        for (UserCoupon update : updates) {
            boolean isOk = lambdaUpdate().set(UserCoupon::getUsedAt, update.getUsedAt())
                .set(UserCoupon::getStatus, update.getStatus())
                .set(UserCoupon::getUsedAmt, update.getUsedAmt())
                .eq(UserCoupon::getId, update.getId())
                .eq(UserCoupon::getStatus, UserCouponStatus.UnUse.getCodeStr()).update();
            Assert.isTrue(isOk, "更新状态失败");
        }
        return true;
    }

    @Override
    public IScroll<UserCouponBuyerVo> scrollByBuyer(UserCouponBuyerScrollRo ro) {
        Page<UserCoupon> page = lambdaQuery().eq(UserCoupon::getOwnerUserId, ro.getOwnerUserId())
            .eq(Objects.nonNull(ro.getExpiredFlag()), UserCoupon::getExpiredFlag, ro.getExpiredFlag())
            .eq(Objects.nonNull(ro.getStatus()), UserCoupon::getStatus, ro.getStatus())
            .gt(CommonEntity::getId, ro.getNextId()).orderByAsc(UserCoupon::getEndAt)
            .page(ro.ofPage());
        return PageUtils.fillScroll(page, UserCoupon::getId).convert(mapping::asUserCouponBuyerVo);
    }

    private List<UserCoupon> listByUserId(Long userId) {
        return lambdaQuery().eq(UserCoupon::getOwnerUserId, userId).list();
    }

}
