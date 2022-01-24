package in.hocg.rabbit.mall.biz.state.maintain;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.rabbit.mall.api.enums.maintain.OrderMaintainStatus;
import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.pojo.ro.PassOrderMaintainRo;
import in.hocg.rabbit.mall.biz.state.ActionUtils;
import in.hocg.rabbit.mall.biz.state.MessageUtils;
import in.hocg.rabbit.mall.biz.state.maintain.action.OrderMaintainAction;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.EnumSet;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class MaintainStateMachine {

    @SneakyThrows
    public StateMachine<OrderMaintainStatus, MaintainEvent> initialization(OrderMaintain entity) {
        StateMachineBuilder.Builder<OrderMaintainStatus, MaintainEvent> builder = StateMachineBuilder.builder();

        builder.configureConfiguration().withConfiguration()
            .autoStartup(true)
//            .listener(listener())
        ;

        builder.configureStates().withStates()
            .initial(ICode.ofThrow(entity.getStatus(), OrderMaintainStatus.class))
            .states(EnumSet.allOf(OrderMaintainStatus.class))
        ;
        OrderMaintainAction commonAction = SpringServletContext.getBean(OrderMaintainAction.class);

        // @formatter:off
        builder.configureTransitions()
            .withExternal().event(MaintainEvent.Pass).source(OrderMaintainStatus.Processing).target(OrderMaintainStatus.Success)
                .action(ActionUtils.throwAction(commonAction)).and()
            .withExternal().event(MaintainEvent.CloseByBuyer).source(OrderMaintainStatus.Processing).target(OrderMaintainStatus.Close)
                .action(ActionUtils.throwAction(commonAction)).and()
            .withExternal().event(MaintainEvent.CloseBySystem).source(OrderMaintainStatus.Processing).target(OrderMaintainStatus.Close)
                .action(ActionUtils.throwAction(commonAction)).and()
            .withExternal().event(MaintainEvent.CloseBySeller).source(OrderMaintainStatus.Processing).target(OrderMaintainStatus.Close)
                .action(ActionUtils.throwAction(commonAction)).and()
        ;
        // @formatter:on
        return builder.build();
    }

    public static void pass(OrderMaintain entity, PassOrderMaintainRo ro) {
        MessageUtils.sendEvent(MaintainStateMachine.initialization(entity), MaintainEvent.Pass, entity, ro);
    }

    public static void closeByBuyer(OrderMaintain entity) {
        MessageUtils.sendEvent(MaintainStateMachine.initialization(entity), MaintainEvent.CloseByBuyer, entity);
    }

    public static void closeBySeller(OrderMaintain entity) {
        MessageUtils.sendEvent(MaintainStateMachine.initialization(entity), MaintainEvent.CloseBySeller, entity);
    }

    public static void closeBySystem(OrderMaintain entity, PassOrderMaintainRo ro) {
        MessageUtils.sendEvent(MaintainStateMachine.initialization(entity), MaintainEvent.CloseBySystem, entity, ro);
    }
}
