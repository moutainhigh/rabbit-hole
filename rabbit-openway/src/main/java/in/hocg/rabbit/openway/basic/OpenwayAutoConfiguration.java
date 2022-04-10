package in.hocg.rabbit.openway.basic;

import in.hocg.rabbit.openway.basic.route.DefaultRouteService;
import in.hocg.rabbit.openway.basic.route.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OpenwayAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RouteService routeService() {
        return new DefaultRouteService();
    }
}
