package com.github.lotus.gateway.filter.authorize;

import com.github.lotus.common.utils.RabbitUtils;
import com.github.lotus.gateway.service.UserService;
import com.github.lotus.gateway.utils.ResultUtils;
import com.github.lotus.usercontext.basic.HeaderConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by hocgin on 2021/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class AuthorizeFilter extends BaseAuthorizeFilter {
    private final UserService userService;

    public AuthorizeFilter(AuthorizeProperties properties, UserService userService) {
        super(properties);
        this.userService = userService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 如果不需要认证
        if (isPermitAllWithIp(request) || isPermitAllWithUri(request)) {
            log.debug("Request is ignored.");
            return chain.filter(exchange);
        }

        String username = request.getHeaders().getFirst(HeaderConstants.USERNAME);

        if (Strings.isBlank(username)) {
            log.warn("用户未登陆");
            return ResultUtils.accountError(exchange);
        }

        if (!isPassAuthorize(request, username) && !RabbitUtils.isSuperAdmin(username)) {
            log.warn("Username:[{}]不具备访问[{}]的权限", username, request.getURI());
            return ResultUtils.accessDenied(exchange);
        }

        return chain.filter(exchange);
    }

    @Override
    protected boolean isPassAuthorize(ServerHttpRequest request, String username) {
        PathContainer pathContainer = request.getPath().pathWithinApplication();

        String servicePrefixPath = pathContainer.subPath(0, 2).toString();
        String requestUriPath = pathContainer.subPath(2).toString();
        String requestMethodName = request.getMethodValue();
        return userService.isPassAuthorize(username, servicePrefixPath, requestMethodName, requestUriPath);
    }
}
