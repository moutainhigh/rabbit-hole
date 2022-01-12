package in.hocg.rabbit.mall.biz.state.orderitem;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.rabbit.mall.api.enums.order.OrderItemMaintainStatus;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import in.hocg.rabbit.mall.biz.state.ActionUtils;
import in.hocg.rabbit.mall.biz.state.MessageUtils;
import in.hocg.rabbit.mall.biz.state.orderitem.action.OrderItemMaintainAction;
import in.hocg.rabbit.mall.biz.state.orderitem.listener.OrderItemMaintainStateListener;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.time.LocalDateTime;
import java.util.EnumSet;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class OrderItemMaintainStateMachine {

    @SneakyThrows
    public StateMachine<OrderItemMaintainStatus, OrderItemMaintainEvent> initialization(OrderItem entity) {
        StateMachineBuilder.Builder<OrderItemMaintainStatus, OrderItemMaintainEvent> builder = StateMachineBuilder.builder();

        builder.configureConfiguration().withConfiguration()
            .autoStartup(true)
            .listener(new OrderItemMaintainStateListener(entity, service()))
        ;

        builder.configureStates().withStates()
            .initial(ICode.ofThrow(entity.getMaintainStatus(), OrderItemMaintainStatus.class))
            .states(EnumSet.allOf(OrderItemMaintainStatus.class))
        ;

        OrderItemMaintainAction commonAction = SpringServletContext.getBean(OrderItemMaintainAction.class);

        // @formatter:off
        builder.configureTransitions()
            // 允许售后
            .withExternal()
                .event(OrderItemMaintainEvent.Start).source(OrderItemMaintainStatus.NotStarted).target(OrderItemMaintainStatus.Starting)
                .action(ActionUtils.throwAction(commonAction)).and()
           // 申请售后
            .withExternal()
                .event(OrderItemMaintainEvent.Apply).source(OrderItemMaintainStatus.Starting).target(OrderItemMaintainStatus.Processing)
                .action(ActionUtils.throwAction(commonAction)).and()
            // 取消申请
            .withExternal()
                .event(OrderItemMaintainEvent.CloseByBuyer).target(OrderItemMaintainStatus.Processing).guard(context-> LocalDateTime.now().isAfter(entity.getPlanMaintainAt())).target(OrderItemMaintainStatus.Expired)
                .action(ActionUtils.throwAction(commonAction)).and()
            .withExternal()
                .event(OrderItemMaintainEvent.CloseBySeller).target(OrderItemMaintainStatus.Processing).guard(context-> LocalDateTime.now().isBefore(entity.getPlanMaintainAt())).target(OrderItemMaintainStatus.Starting)
                .action(ActionUtils.throwAction(commonAction)).and()
            // 完成售后
            .withExternal()
                .event(OrderItemMaintainEvent.Success).source(OrderItemMaintainStatus.Processing).target(OrderItemMaintainStatus.Maintained)
                .action(ActionUtils.throwAction(commonAction)).and()
        ;
        // @formatter:on
        return builder.build();
    }

    public void start(OrderItem entity) {
        MessageUtils.sendEvent(OrderItemMaintainStateMachine.initialization(entity), OrderItemMaintainEvent.Start, entity);
    }

    public void apply(OrderItem entity) {
        MessageUtils.sendEvent(OrderItemMaintainStateMachine.initialization(entity), OrderItemMaintainEvent.Apply, entity);
    }

    public void closeByBuyer(OrderItem entity) {
        MessageUtils.sendEvent(OrderItemMaintainStateMachine.initialization(entity), OrderItemMaintainEvent.CloseByBuyer, entity);
    }

    public void closeBySeller(OrderItem entity) {
        MessageUtils.sendEvent(OrderItemMaintainStateMachine.initialization(entity), OrderItemMaintainEvent.CloseBySeller, entity);
    }

    public void expire(OrderItem entity) {
        MessageUtils.sendEvent(OrderItemMaintainStateMachine.initialization(entity), OrderItemMaintainEvent.Expire, entity);
    }

    public void success(OrderItem entity) {
        MessageUtils.sendEvent(OrderItemMaintainStateMachine.initialization(entity), OrderItemMaintainEvent.Success, entity);
    }

    public OrderItemService service() {
        return SpringServletContext.getBean(OrderItemService.class);
    }
}
