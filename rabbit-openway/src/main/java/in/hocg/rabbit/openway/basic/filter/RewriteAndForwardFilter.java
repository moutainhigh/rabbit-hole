package in.hocg.rabbit.openway.basic.filter;

import cn.hutool.json.JSONUtil;
import in.hocg.rabbit.openway.constants.OpenwayContants;
import in.hocg.rabbit.openway.basic.context.GatewayContext;
import in.hocg.rabbit.openway.basic.route.data.RequestBody;
import in.hocg.rabbit.openway.utils.OpenwayUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 * <p>
 * 1. 重写请求体
 * 2. 转发
 * 3. 重写响应体
 *
 * @author hocgin
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 3)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RewriteAndForwardFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        GatewayContext context = exchange.getAttribute(GatewayContext.NAME);
        assert context != null;
        RequestBody body = JSONUtil.toBean(context.getBody(), RequestBody.class);
        return chain.filter(rewriteResponse(rewriteRequest(exchange, body), body));
    }

    private ServerWebExchange rewriteResponse(ServerWebExchange exchange, RequestBody body) {
        ServerHttpResponse oldResponse = exchange.getResponse();
        DataBufferFactory factory = oldResponse.bufferFactory();
        ServerHttpResponse newResponse = new ServerHttpResponseDecorator(oldResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> withBody) {
                if (withBody instanceof Mono) {
                    Mono<? extends DataBuffer> fluxBody = (Mono<? extends DataBuffer>) withBody;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);

                        String responseBody = new String(bytes, StandardCharsets.UTF_8);
                        Map<String, Object> result = new HashMap<>();
                        result.put(OpenwayContants.SIGN, OpenwayUtils.getSignStr(responseBody));
                        result.put(body.getMethod(), JSONUtil.parseObj(responseBody));
                        return factory.wrap(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
                    }));
                }
                return super.writeWith(withBody);
            }
        };
        return exchange.mutate().response(newResponse).build();
    }

    private ServerWebExchange rewriteRequest(ServerWebExchange exchange, RequestBody body) {
        ServerHttpRequest oldRequest = exchange.getRequest();
        URI newUri = UriComponentsBuilder.fromHttpRequest(oldRequest).path(body.getMethod()).build().toUri();
        ServerHttpRequest newRequest = oldRequest.mutate().uri(newUri).build();
        String bizContent = body.getBizContent();

        DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
        DataBuffer dataBuffer = factory.allocateBuffer();
        if (Objects.nonNull(bizContent)) {
            byte[] bytes = bizContent.getBytes(StandardCharsets.UTF_8);
            dataBuffer = factory.allocateBuffer(bytes.length);
            dataBuffer.write(bytes);
        }

        Flux<DataBuffer> bodyFlux = Flux.just(dataBuffer);
        newRequest = new ServerHttpRequestDecorator(newRequest) {
            @Override
            public Flux<DataBuffer> getBody() {
                return bodyFlux;
            }
        };

        return exchange.mutate().request(newRequest).build();
    }

}
