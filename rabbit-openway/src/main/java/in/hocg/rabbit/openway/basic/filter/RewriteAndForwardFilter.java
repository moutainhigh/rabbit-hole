package in.hocg.rabbit.openway.basic.filter;

import cn.hutool.core.util.StrUtil;
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
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

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
        log.debug("进入 RewriteAndForwardFilter");
        GatewayContext context = exchange.getAttribute(GatewayContext.NAME);
        if (Objects.isNull(context)) {
            return chain.filter(exchange);
        }
        RequestBody body = context.getRequestBody();
        Optional<AppInfo> appOpt = routeService.getAppid(body.getAppid());
        if (appOpt.isEmpty()) {
            return Mono.error(new Exception("请检查应用编号是否正确"));
        }
        AppInfo appInfo = appOpt.get();
        if (appInfo.getExpired()) {
            return Mono.error(new Exception("该应用授权已过期"));
        }

        ServerWebExchange serverWebExchange = rewriteResponse(rewriteRequest(exchange, body, appInfo.getUsername()), body);
        log.debug("结束 RewriteAndForwardFilter");
        return chain.filter(serverWebExchange);
    }

    private ServerWebExchange rewriteRequest(ServerWebExchange exchange, RequestBody body, String username) {
        log.debug("进入 rewriteRequest");

        ServerHttpRequest oldRequest = exchange.getRequest();
        URI newUri = UriComponentsBuilder.fromHttpRequest(oldRequest).path(body.getMethod()).build().toUri();

        ServerHttpRequest newRequest = oldRequest.mutate().uri(newUri)
            .header(HeaderConstants.SOURCE, OpenwayContants.SOURCE)
            .header(HeaderConstants.USERNAME, username)
            .build();

        DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
        return exchange.mutate().request(new ServerHttpRequestDecorator(newRequest) {
            @Override
            public Flux<DataBuffer> getBody() {
                return super.getBody().map(dataBuffer -> {
                    byte[] bytes = StrUtil.nullToEmpty(body.getBizContent()).getBytes();
                    DefaultDataBuffer buffer = factory.allocateBuffer(bytes.length);
                    buffer.write(bytes);
                    return buffer;
                });
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                //由于修改了请求体的body，导致content-length长度不确定，因此需要删除原先的content-length
                httpHeaders.remove(HttpHeaders.CONTENT_LENGTH);
                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                return httpHeaders;
            }
        }).build();
    }

    private ServerWebExchange rewriteResponse(ServerWebExchange exchange, RequestBody body) {
        log.debug("进入 rewriteResponse");
        ServerHttpResponse oldResponse = exchange.getResponse();
        DataBufferFactory factory = oldResponse.bufferFactory();
        String method = body.getMethod();
        return exchange.mutate().response(new ServerHttpResponseDecorator(oldResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> withBody) {
                if (withBody instanceof Flux) {
                    return super.writeWith(Flux.from(withBody).buffer().map(dataBuffers -> {
                        DefaultDataBufferFactory bufferFactory = new DefaultDataBufferFactory();
                        DefaultDataBuffer dataBuffer = bufferFactory.join(dataBuffers);
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);

                        String responseBody = new String(bytes, StandardCharsets.UTF_8);
                        log.debug("-服务返回-> responseBody: {}", responseBody);
                        String finalResponseBody;
                        if (JSONUtil.isJson(responseBody)) {
                            finalResponseBody = JSONUtil.toJsonStr(ExceptionUtils.result(method, responseBody));
                        } else {
                            finalResponseBody = JSONUtil.toJsonStr(ExceptionUtils.result(method, Result.fail("系统繁忙，请稍后再试")));
                        }
                        log.debug("-最终返回-> finalResponseBody: {}", finalResponseBody);
                        HttpHeaders headers = getDelegate().getHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.setContentLength(finalResponseBody.getBytes().length);
                        return factory.wrap(finalResponseBody.getBytes());
                    }));
                }
                return ExceptionUtils.handleException(exchange, method, new UnsupportedOperationException("不支持的类型：" + withBody.getClass()));
            }
        }).build();
    }


}
