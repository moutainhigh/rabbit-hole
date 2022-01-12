package in.hocg.rabbit.mall.biz.state;

import in.hocg.boot.utils.LambdaUtils;
import lombok.experimental.UtilityClass;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class MessageUtils {

    private <T> Message<T> buildMessage(T event, Object entity, Object ro) {
        return MessageBuilder.withPayload(event)
            .setHeader(LambdaUtils.getPropertyName(Headers::getEntity), entity)
            .setHeader(LambdaUtils.getPropertyName(Headers::getRo), ro)
            .build();
    }

    public <S, E> void sendEvent(StateMachine<S, E> machine, E event, Object entity) {
        MessageUtils.sendEvent(machine, event, entity, null);
    }

    public <S, E> void sendEvent(StateMachine<S, E> machine, E event, Object entity, Object ro) {
        machine.sendEvent(MessageUtils.buildMessage(event, entity, ro));
        ActionUtils.throwException(machine);
    }
}
