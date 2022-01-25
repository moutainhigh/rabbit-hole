package in.hocg.rabbit.mall.biz.state.order.action;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import in.hocg.rabbit.mall.api.enums.order.*;
import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.service.OrderService;
import in.hocg.rabbit.mall.biz.state.CommonAction;
import in.hocg.rabbit.mall.biz.state.order.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderTradeStatusAction extends CommonAction<OrderTradeStatus, OrderEvent> {
    private final OrderService service;

    @Override
    public void execute(StateContext<OrderTradeStatus, OrderEvent> context) {
        OrderEvent event = getEvent(context);
        String targetCode = getTarget(context).getCodeStr();
        String sourceCode = getSource(context).getCodeStr();
        Order entity = getEntity(context);

        Order update = new Order();

        // 关单
        if (Lists.newArrayList(OrderEvent.CloseByBuyer, OrderEvent.CloseBySeller, OrderEvent.CloseBySystem).contains(event)) {
            update.setOrderStatus(OrderStatus.Close.getCodeStr());
        }
        // 退款
        else if (Lists.newArrayList(OrderEvent.RefundByBuyer, OrderEvent.RefundBySeller).contains(event)) {
            update.setOrderStatus(OrderStatus.Close.getCodeStr());
            update.setRefundStatus(OrderRefundStatus.Returned.getCodeStr());
        }
        // 支付
        else if (Lists.newArrayList(OrderEvent.Payed).contains(event)) {
            update.setPayStatus(OrderPayStatus.Payed.getCodeStr());
        }
        // 收货
        else if (Lists.newArrayList(OrderEvent.ReceivedByBuyer, OrderEvent.ReceivedBySystem).contains(event)) {
            update.setOrderStatus(OrderStatus.Success.getCodeStr());
            update.setReceiveStatus(OrderReceiveStatus.Received.getCodeStr());
        }
        // 发货
        else if (Lists.newArrayList(OrderEvent.Shipped).contains(event)) {
            update.setDeliveryStatus(OrderDeliveryStatus.Shipped.getCodeStr());
        } else {
            throw new UnsupportedOperationException("未定义的事件：" + event);
        }

        update.setTradeStatus(targetCode);
        boolean isOk = service.lambdaUpdate().eq(Order::getId, entity.getId())
            .eq(Order::getTradeStatus, sourceCode).update(update);
        Assert.isTrue(isOk, "更新状态失败");
    }
}
