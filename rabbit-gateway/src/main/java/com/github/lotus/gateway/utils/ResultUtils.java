package com.github.lotus.gateway.utils;

import cn.hutool.json.JSONUtil;
import in.hocg.boot.web.result.Result;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Created by hocgin on 2020/8/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@UtilityClass
public class ResultUtils {

    /**
     * 被拒绝访问, 没有权限
     * @param exchange
     * @return
     */
    public Mono<Void> accessDenied(ServerWebExchange exchange) {
        if (CommonUtils.isAjaxRequest(exchange)) {
            return result(exchange, Result.fail("不具备访问权限"));
        }
        log.warn("暂未配置[被拒绝访问, 没有权限]的页面");
        throw new RuntimeException("暂未配置[被拒绝访问, 没有权限]的页面");
    }

    public Mono<Void> result(ServerWebExchange exchange, Object result) {
        ServerHttpResponse response = exchange.getResponse();
        DataBuffer buffer = response.bufferFactory()
            .wrap(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(buffer));
    }
}
