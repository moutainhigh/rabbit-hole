package in.hocg.rabbit.mall.biz.state;

import in.hocg.boot.utils.LambdaUtils;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class CommonAction<S, E> implements Action<S, E> {

    public E getEvent(StateContext<S, E> context) {
        return context.getEvent();
    }

    public S getTarget(StateContext<S, E> context) {
        return context.getTarget().getId();
    }

    public S getSource(StateContext<S, E> context) {
        return context.getSource().getId();
    }

    @SuppressWarnings("unchecked")
    public <T> T getEntity(StateContext<S, E> context) {
        return (T) context.getMessageHeader(LambdaUtils.getPropertyName(Headers::getEntity));
    }

    @SuppressWarnings("unchecked")
    public <T> T getRo(StateContext<S, E> context) {
        return (T) context.getMessageHeader(LambdaUtils.getPropertyName(Headers::getRo));
    }
}
