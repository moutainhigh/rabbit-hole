package in.hocg.rabbit.openway.basic.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.openway.basic.data.AppInfo;
import in.hocg.rabbit.openway.basic.route.RouteService;
import in.hocg.rabbit.openway.constants.OpenwayContants;
import in.hocg.rabbit.openway.basic.context.GatewayContext;
import in.hocg.rabbit.openway.basic.data.RequestBody;
import in.hocg.rabbit.openway.constants.OrderedConstants;
import in.hocg.rabbit.openway.utils.ExceptionUtils;
import in.hocg.rabbit.openway.utils.OpenwayUtils;
import in.hocg.rabbit.usercontext.basic.HeaderConstants;
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
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

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
@Order(OrderedConstants.REWRITE_FORWARD_ORDERED)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RewriteAndForwardFilter implements WebFilter {
    private final RouteService routeService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        GatewayContext context = exchange.getAttribute(GatewayContext.NAME);
        assert context != null;
        RequestBody body = context.getRequestBody();
        return chain.filter(rewriteResponse(rewriteRequest(exchange, body), body));
    }

    private ServerWebExchange rewriteResponse(ServerWebExchange exchange, RequestBody body) {
        ServerHttpResponse oldResponse = exchange.getResponse();
        DataBufferFactory factory = oldResponse.bufferFactory();
        Function<DataBuffer, DataBuffer> dataBufferMapHandle = dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);

            String responseBody = new String(bytes, StandardCharsets.UTF_8);
            if (!JSONUtil.isJson(responseBody)) {
                responseBody = JSONUtil.toJsonStr(Result.fail("系统繁忙，请稍后再试"));
            }
            return factory.wrap(JSONUtil.toJsonStr(ExceptionUtils.result(body.getMethod(), responseBody)).getBytes(StandardCharsets.UTF_8));
        };
        return exchange.mutate().response(new ServerHttpResponseDecorator(oldResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> withBody) {
                if (withBody instanceof Flux) {
                    return super.writeWith(((Flux<? extends DataBuffer>) withBody).map(dataBufferMapHandle));
                } else if (withBody instanceof Mono) {
                    return super.writeWith(((Mono<? extends DataBuffer>) withBody).map(dataBufferMapHandle));
                }
                log.error("不支持的类型：{}", withBody.getClass());
                return ExceptionUtils.handleException(exchange, body.getMethod(), new UnsupportedOperationException("不支持操作"));
            }
        }).build();
    }

    private ServerWebExchange rewriteRequest(ServerWebExchange exchange, RequestBody body) {
        String username = routeService.getAppid(body.getAppid()).map(AppInfo::getUsername).orElse(null);

        ServerHttpRequest oldRequest = exchange.getRequest();
        URI newUri = UriComponentsBuilder.fromHttpRequest(oldRequest).path(body.getMethod()).build().toUri();
        ServerHttpRequest newRequest = oldRequest.mutate().uri(newUri)
            .header(HeaderConstants.SOURCE, OpenwayContants.SOURCE)
            .header(HeaderConstants.USERNAME, username)
            .build();

        DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
        DataBuffer dataBuffer = factory.allocateBuffer();
        String bizContent = body.getBizContent();
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
