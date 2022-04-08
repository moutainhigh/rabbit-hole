package in.hocg.rabbit.openway.route;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 * 路由刷新事件发布者
 *
 * @author hocgin
 */
public class GatewayRoutesRefresher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void refreshRoutes() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }
}
