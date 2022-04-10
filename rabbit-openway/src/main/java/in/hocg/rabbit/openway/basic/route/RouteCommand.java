package in.hocg.rabbit.openway.basic.route;

import cn.hutool.extra.spring.SpringUtil;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RouteCommand implements CommandLineRunner {
    private final GatewayRouteLocator locator;

    @Async
    @Override
    public void run(String... args) throws Exception {
        locator.reloadRoutes();
    }
}
