package in.hocg.rabbit.openway.route;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.Objects;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
//@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GatewayRouteLocator implements RouteLocator, InitializingBean {
    private final GatewayRoutesRefresher routesRefresher;
    private final RouteLocatorBuilder builder;
    private final RouteService routeService;

    private Flux<Route> route;
    private RouteLocatorBuilder.Builder routesBuilder;

    public void clearRoutes() {
        routesBuilder = builder.routes();
    }

    private void loadRoutes() {
        clearRoutes();
        if (Objects.nonNull(routesBuilder)) {
            routeService.listAll().parallelStream().forEach(service -> {
                String serviceId = service.getServiceId();
                ServiceRoute.Api serviceDefinition = service.getApi();
                if (serviceDefinition == null) {
                    log.error("无此服务配置信息：" + serviceId);
                }
//                URI uri = UriComponentsBuilder.fromHttpUrl(serviceDefinition.getRoutePath()).build().toUri();
//                routesBuilder.route(serviceId, r -> r.path(serviceDefinition.getRequestPath()).uri(uri));
            });
            this.route = routesBuilder.build().getRoutes();
        }
        routesRefresher.refreshRoutes();
    }


    @Override
    public Flux<Route> getRoutes() {
        return route;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        clearRoutes();
    }
}
