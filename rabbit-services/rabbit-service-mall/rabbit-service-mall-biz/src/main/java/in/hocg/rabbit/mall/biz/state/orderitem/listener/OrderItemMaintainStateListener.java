package in.hocg.rabbit.mall.biz.state.orderitem.listener;

import in.hocg.rabbit.mall.api.enums.orderitem.OrderItemMaintainStatus;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import in.hocg.rabbit.mall.biz.state.orderitem.OrderItemMaintainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.statemachine.transition.TransitionKind;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderItemMaintainStateListener extends StateMachineListenerAdapter<OrderItemMaintainStatus, OrderItemMaintainEvent> {
    private final OrderItem entity;
    private final OrderItemService service;

    @Override
    public void transition(Transition<OrderItemMaintainStatus, OrderItemMaintainEvent> transition) {
        TransitionKind kind = transition.getKind();
        if (TransitionKind.INITIAL.equals(kind)) {
            return;
        }
        log.debug("订单项状态机转换：{} -> {}", transition.getSource().getId(), transition.getTarget().getId());
    }

}
