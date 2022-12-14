package in.hocg.rabbit.mall.biz.state;

import in.hocg.boot.utils.exception.ServiceException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.action.Actions;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@UtilityClass
public class ActionUtils {
    public static String EXCEPTION = "exception";

    public <S, E> Action<S, E> throwAction(Action<S, E> action) throws Exception {
        return Actions.errorCallingAction(action, context -> {
            Exception exception = context.getException();
            log.error("状态机异常", exception);
            context.getStateMachine().setStateMachineError(exception);
            context.getExtendedState().getVariables().put(EXCEPTION, exception);
        });
    }

    public void throwException(StateMachine<?, ?> machine) {
        if (machine.hasStateMachineError()) {
            throw ServiceException.wrap(machine.getExtendedState().get(EXCEPTION, Exception.class));
        }
    }
}
