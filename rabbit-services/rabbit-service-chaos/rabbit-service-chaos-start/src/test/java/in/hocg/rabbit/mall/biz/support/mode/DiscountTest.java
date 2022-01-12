package in.hocg.rabbit.mall.biz.support.mode;

import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.mall.biz.support.mode.impl.TestOrderContext;
import in.hocg.rabbit.mall.biz.support.mode.impl.discount.AllNotDiscount;
import in.hocg.rabbit.mall.biz.support.mode.impl.discount.AllPassDiscount;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2022/1/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class DiscountTest {

    public static void main(String[] args) {
        OrderSupport order = OrderSupport.create(
            new OrderSupport.Item(1L, BigDecimal.ONE),
            new OrderSupport.Item(2L, BigDecimal.ONE),
            new OrderSupport.Item(3L, BigDecimal.ONE));
        TestOrderContext context = new TestOrderContext();
        OrderContext result = order.use(context, List.of(
            new AllPassDiscount(1L),
            new AllPassDiscount(2L),
            new AllNotDiscount(3L)
        ), List.of(1L, 2L));

        System.out.println(StrUtil.format("订单, 原价: {}", result.getTotalSaleAmtByOrder()));
        System.out.println(StrUtil.format("订单, 优惠金额: {}", result.getDiscountAmtByOrder()));
        System.out.println(StrUtil.format("订单, 优惠后金额: {}", result.getTotalRealAmtByOrder()));

        result.getOrderItem().forEach(item -> {
            Serializable id = item.getId();
            System.out.println(StrUtil.format("订单明细, 原价: {}", result.getTotalAmtByOrderItem(id)));
            System.out.println(StrUtil.format("订单明细, 优惠金额: {}", result.getDiscountAmtByOrderItem(id)));
            System.out.println(StrUtil.format("订单明细, 优惠后金额: {}", result.getRealAmtByOrderItem(id)));
        });

        System.out.println(StrUtil.format("使用的优惠: {}", result.getUsedDiscount()));
        System.out.println(StrUtil.format("未使用的优惠: {}", result.getNoUsedDiscount()));
        System.out.println(StrUtil.format("可用的优惠: {}", result.getAvailableDiscount()));
        System.out.println(StrUtil.format("不可使用的优惠: {}", result.getNoAvailableDiscount()));
        System.out.println(StrUtil.format("剩余可用的优惠: {}", result.getSurplusAvailableDiscount()));

    }
}
