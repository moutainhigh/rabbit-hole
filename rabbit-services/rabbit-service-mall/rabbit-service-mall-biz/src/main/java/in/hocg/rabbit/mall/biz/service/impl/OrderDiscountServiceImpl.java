package in.hocg.rabbit.mall.biz.service.impl;

import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.mall.biz.entity.OrderDiscount;
import in.hocg.rabbit.mall.biz.entity.UserCoupon;
import in.hocg.rabbit.mall.biz.mapper.OrderDiscountMapper;
import in.hocg.rabbit.mall.biz.service.OrderDiscountService;
import in.hocg.rabbit.mall.biz.service.UserCouponService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public void useCoupon(Long id, Long userCouponId) {
        UserCoupon userCoupon = userCouponService.getById(userCouponId);
        OrderDiscount orderDiscount = new OrderDiscount();
        orderDiscount.setOrderId(id);
        orderDiscount.setTitle(userCoupon.getCouponNo());
        orderDiscount.setDiscountAmt(userCoupon.getUsedAmt());
        orderDiscount.setRefId(userCouponId);
        orderDiscount.setRefType(RefType.UserCoupon.getCodeStr());
        this.validInsert(orderDiscount);
    }
}
