package in.hocg.rabbit.openway.basic.filter;

import in.hocg.rabbit.openway.basic.context.GatewayContext;
import in.hocg.rabbit.openway.basic.data.RequestBody;
import in.hocg.rabbit.openway.constants.OrderedConstants;
import in.hocg.rabbit.openway.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@Order(OrderedConstants.PRE_CHECK_ORDERED)
public class PreCheckFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.debug("PreCheckFilter");
        ServerHttpRequest request = exchange.getRequest();
        GatewayContext context = exchange.getAttribute(GatewayContext.NAME);
        assert context != null;
        RequestBody body = context.getRequestBody();
        String method = body.getMethod();

        // 设置了黑名单并被黑名单拦截
        if (isPermitBlackAllWithIp(request, method)) {
            return ExceptionUtils.handleException(exchange, method, new RuntimeException("请求被拒绝"));
        }

        // 设置了白名单并未被白名单允许
        if (!isPermitWhiteAllWithIp(request, method)) {
            return ExceptionUtils.handleException(exchange, method, new RuntimeException("请求被拒绝"));
        }

        return chain.filter(exchange);
    }

    public boolean isPermitBlackAllWithIp(ServerHttpRequest request, String method) {
        return false;
    }

    public boolean isPermitWhiteAllWithIp(ServerHttpRequest request, String method) {
        return true;
    }
}
