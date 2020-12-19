package com.github.lotus.gateway.filter;

import com.github.lotus.common.utils.JwtUtils;
import com.github.lotus.usercontext.basic.HeaderConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class AuthorizeTokenFilter implements GlobalFilter, Ordered {
    static final String AUTHORIZATION_HEADER = "Authorization";
    static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String bearerToken = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER);

        if (Strings.isNotBlank(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            Optional<String> usernameOpt = this.getUsername(bearerToken);
            if (usernameOpt.isPresent()) {
                ServerHttpRequest newRequest = exchange.getRequest().mutate().header(HeaderConstants.USERNAME, usernameOpt.get()).build();
                exchange = exchange.mutate().request(newRequest).build();
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

    private Optional<String> getUsername(String bearerToken) {
        String token = bearerToken.replaceFirst(BEARER_PREFIX, "");
        String username = JwtUtils.decode(token);
        if (Strings.isBlank(username)) {
            return Optional.empty();
        }
        return Optional.of(username);
    }
}
