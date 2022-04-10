package in.hocg.rabbit.openway.basic.filter;

import in.hocg.rabbit.openway.basic.context.GatewayContext;
import in.hocg.rabbit.openway.constants.OrderedConstants;
import in.hocg.rabbit.openway.utils.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 * <p>
 * 解析请求体
 * - AnalyzeFilter
 * - ValidAndForwardFilter
 *
 * @author hocgin
 */
@Component
@Order(OrderedConstants.CONTEXT_ORDERED)
public class OpenwayContextFilter implements WebFilter {
    private static final List<HttpMessageReader<?>> MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MediaType contentType = request.getHeaders().getContentType();

        GatewayContext context = new GatewayContext().setPath(request.getPath().pathWithinApplication().value());
        exchange.getAttributes().put(GatewayContext.NAME, context);

        if (HttpMethod.POST.equals(request.getMethod()) && MediaType.APPLICATION_JSON.includes(contentType)) {
            return handle(exchange, chain, context);
        }
        return Mono.error(new Exception("不支持的请求类型"));
    }

    public Mono<Void> handle(ServerWebExchange exchange, WebFilterChain chain, GatewayContext context) {
        return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            DataBufferUtils.release(dataBuffer);
            Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                DataBufferUtils.retain(buffer);
                return Mono.just(buffer);
            });
            ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return cachedFlux;
                }
            };
            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
            return ServerRequest.create(mutatedExchange, MESSAGE_READERS)
                .bodyToMono(String.class)
                .doOnNext(context::setBody)
                .then(chain.filter(mutatedExchange));
        });
    }
}


