package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.mall.api.enums.coupon.UserCouponStatus;
import in.hocg.rabbit.mall.biz.entity.OrderDiscount;
import in.hocg.rabbit.mall.biz.entity.UserCoupon;
import in.hocg.rabbit.mall.biz.mapper.OrderDiscountMapper;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.service.OrderDiscountService;
import in.hocg.rabbit.mall.biz.service.UserCouponService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * [订单模块] 订单优惠详项表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderDiscountServiceImpl extends AbstractServiceImpl<OrderDiscountMapper, OrderDiscount> implements OrderDiscountService {
    private final UserCouponService userCouponService;

    @Override
    public List<OrderDiscount> listByOrderId(Long orderId) {
        return lambdaQuery().eq(OrderDiscount::getOrderId, orderId).list();
    }


    @Override
    public void usedDiscount(Long orderId, List<CalcOrderVo.DiscountVo> discounts) {
        LocalDateTime now = LocalDateTime.now();
        List<UserCoupon> useUpdate = discounts.stream().filter(discountVo -> RefType.UserCoupon.eq(discountVo.getRefType()))
            .map(discountVo -> {
                UserCoupon result = new UserCoupon();
                result.setId(discountVo.getRefId());
                return result.setUsedAt(now).setStatus(UserCouponStatus.Used.getCodeStr())
                    .setUsedAmt(discountVo.getDiscountAmt());
            }).collect(Collectors.toList());
        userCouponService.updateUsedStatus(useUpdate);
        Assert.isTrue(discounts.size() == useUpdate.size(), "优惠信息不匹配");
    }

    @Override
    public void refundDiscountByOrderId(Long orderId) {
        List<OrderDiscount> discounts = this.listByOrderId(orderId);
        List<Long> userCouponId = discounts.stream()
            .filter(discountVo -> RefType.UserCoupon.eq(discountVo.getRefType()))
            .map(OrderDiscount::getRefId).collect(Collectors.toList());
        userCouponService.updateUnusedStatus(userCouponId);
        Assert.isTrue(discounts.size() == userCouponId.size(), "优惠信息不匹配");
    }
}
