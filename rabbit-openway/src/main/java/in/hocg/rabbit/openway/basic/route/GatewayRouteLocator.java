package in.hocg.rabbit.openway.basic.route;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GatewayRouteLocator implements RouteLocator, InitializingBean {
    private final GatewayRoutesRefresher routesRefresher;
    private final RouteLocatorBuilder builder;
    private final RouteService routeService;

    private Flux<Route> route;
    private RouteLocatorBuilder.Builder routesBuilder;

    public void clearRoutes() {
        routesBuilder = builder.routes();
        if (Objects.isNull(route)) {
            this.route = routesBuilder.build().getRoutes();
        }
    }

    public void reloadRoutes() {
        clearRoutes();
        if (Objects.nonNull(routesBuilder)) {
            routeService.listAll().forEach(service -> {
                String apiId = service.getApiId();
                String mapTargetUri = service.getMapTargetUri();
                String mapTargetPath = service.getMapTargetPath();
                routesBuilder.route(p -> p.path(StrUtil.format("/{}", apiId)).filters(f ->
                    f.filter((exchange, chain) -> chain.filter(exchange.mutate().request(exchange.getRequest().mutate().path(mapTargetPath).build()).build()))).uri(mapTargetUri)
                );
            });
        }
        this.route = routesBuilder.build().getRoutes();
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
