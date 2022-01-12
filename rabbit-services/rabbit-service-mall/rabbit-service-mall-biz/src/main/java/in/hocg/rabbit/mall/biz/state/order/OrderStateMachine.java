package in.hocg.rabbit.mall.biz.state.order;

import in.hocg.boot.utils.LambdaUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.rabbit.mall.api.enums.order.OrderTradeStatus;
import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import in.hocg.rabbit.mall.biz.state.ActionUtils;
import in.hocg.rabbit.mall.biz.state.MessageUtils;
import in.hocg.rabbit.mall.biz.state.order.action.*;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

import java.util.EnumSet;

/**
 * Created by hocgin on 2022/1/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class OrderStateMachine {

    @SneakyThrows
    public StateMachine<OrderTradeStatus, OrderEvent> initialization(Order entity) {
        StateMachineBuilder.Builder<OrderTradeStatus, OrderEvent> builder = StateMachineBuilder.builder();

        builder.configureConfiguration().withConfiguration()
            .autoStartup(true)
            .listener(listener())
        ;

        builder.configureStates().withStates()
            .initial(ICode.ofThrow(entity.getTradeStatus(), OrderTradeStatus.class))
            .states(EnumSet.allOf(OrderTradeStatus.class))
        ;

        OrderTradeStatusAction commonAction = SpringServletContext.getBean(OrderTradeStatusAction.class);
        // @formatter:off
        builder.configureTransitions()
            // 支付
            .withExternal()
                .event(OrderEvent.Payed).source(OrderTradeStatus.WaitPay).target(OrderTradeStatus.WaitShip)
                .action(ActionUtils.throwAction(commonAction)).and()
            // 发货
            .withExternal()
                .event(OrderEvent.Shipped).source(OrderTradeStatus.WaitShip).target(OrderTradeStatus.WaitReceived)
                .action(ActionUtils.throwAction(commonAction)).and()
            // 收货
            .withExternal()
                .event(OrderEvent.ReceivedByBuyer).source(OrderTradeStatus.WaitReceived).target(OrderTradeStatus.Success)
                .action(ActionUtils.throwAction(commonAction)).and()
            .withExternal()
                .event(OrderEvent.ReceivedBySystem).source(OrderTradeStatus.WaitReceived).target(OrderTradeStatus.Success)
                .action(ActionUtils.throwAction(commonAction)).and()
            // 关闭待支付
            .withExternal()
                .event(OrderEvent.CloseByBuyer).source(OrderTradeStatus.WaitPay).target(OrderTradeStatus.Closed)
                .action(ActionUtils.throwAction(commonAction)).and()
            .withExternal()
                .event(OrderEvent.CloseBySeller).source(OrderTradeStatus.WaitPay).target(OrderTradeStatus.Closed)
                .action(ActionUtils.throwAction(commonAction)).and()
            .withExternal()
                .event(OrderEvent.CloseBySystem).source(OrderTradeStatus.WaitPay).target(OrderTradeStatus.Closed)
                .action(ActionUtils.throwAction(commonAction)).and()
            // 退款待发货
            .withExternal()
                .event(OrderEvent.RefundBySeller).source(OrderTradeStatus.WaitShip).target(OrderTradeStatus.Closed)
                .action(ActionUtils.throwAction(commonAction)).and()
            .withExternal()
                .event(OrderEvent.RefundByBuyer).source(OrderTradeStatus.WaitShip).target(OrderTradeStatus.Closed)
                .action(ActionUtils.throwAction(commonAction)).and()
        ;
        // @formatter:on
        return builder.build();
    }

    public void payed(Order entity) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.Payed, entity);
    }

    public void shipped(Order entity) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.Shipped, entity);
    }

    public void receivedByBuyer(Order entity) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.ReceivedByBuyer, entity);
    }

    public void receivedBySystem(Order entity, ReceivedOrderClientRo ro) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.ReceivedBySystem, entity, ro);
    }

    public void closeByBuyer(Order entity) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.CloseByBuyer, entity);
    }

    public void closeBySeller(Order entity) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.CloseBySeller, entity);
    }

    public void closeBySystem(Order entity, CloseOrderManageRo ro) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.CloseBySystem, entity, ro);
    }

    public void refundByBuyer(Order entity, RefundOrderClientRo ro) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.RefundByBuyer, entity, ro);
    }

    public void refundBySeller(Order entity) {
        MessageUtils.sendEvent(OrderStateMachine.initialization(entity), OrderEvent.RefundBySeller, entity);
    }

    public StateMachineListener<OrderTradeStatus, OrderEvent> listener() {
        return new StateMachineListenerAdapter<>();
    }

}
