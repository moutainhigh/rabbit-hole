package in.hocg.rabbit.mall.biz.state.orderitem.action;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.mall.api.enums.order.OrderItemMaintainStatus;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import in.hocg.rabbit.mall.biz.state.CommonAction;
import in.hocg.rabbit.mall.biz.state.orderitem.OrderItemMaintainEvent;
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
public class OrderItemMaintainAction extends CommonAction<OrderItemMaintainStatus, OrderItemMaintainEvent> {
    private final OrderItemService service;

    @Override
    public void execute(StateContext<OrderItemMaintainStatus, OrderItemMaintainEvent> context) {
        String targetCode = getTarget(context).getCodeStr();
        String sourceCode = getSource(context).getCodeStr();
        OrderItem entity = getEntity(context);

        OrderItem update = new OrderItem();
        update.setMaintainStatus(targetCode);
        boolean isOk = service.lambdaUpdate().eq(OrderItem::getId, entity.getId())
            .eq(OrderItem::getMaintainStatus, sourceCode).update(update);
        Assert.isTrue(isOk, "更新状态失败");
    }

}
