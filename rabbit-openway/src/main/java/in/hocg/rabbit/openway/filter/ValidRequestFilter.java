package in.hocg.rabbit.openway.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import in.hocg.rabbit.openway.context.GatewayContext;
import in.hocg.rabbit.openway.dto.RequestBody;
import in.hocg.rabbit.openway.route.RouteService;
import in.hocg.rabbit.openway.utils.ExceptionUtils;
import in.hocg.rabbit.openway.utils.OpenwayUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 * <p>
 * 1. 校验时间戳
 * 2. 校验商户
 * 3. 校验签名
 *
 * @author hocgin
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ValidRequestFilter implements WebFilter {
    private final RouteService routeService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        GatewayContext context = exchange.getAttribute(GatewayContext.NAME);
        assert context != null;
        String requestBody = context.getBody();
        RequestBody body = JSONUtil.toBean(requestBody, RequestBody.class);

        // 校验必填字段
        if (!validRequired(body)) {
            return ExceptionUtils.handleException(exchange, new RuntimeException("请求参数不能为空"));
        }

        // 校验时间戳
        if (!validTimestamp(body)) {
            return ExceptionUtils.handleException(exchange, new RuntimeException("时间戳校验失败"));
        }

        // 校验接口权限
        if (!validAllow(body)) {
            return ExceptionUtils.handleException(exchange, new RuntimeException("接口不允许访问"));
        }

        // 校验签名
        if (!validSign(requestBody, body)) {
            return ExceptionUtils.handleException(exchange, new RuntimeException("签名校验失败"));
        }

        return chain.filter(exchange);
    }

    private boolean validAllow(RequestBody body) {
//        todo return routeService.hasAuthority(body.getAppid(), body.getMethod());
        return true;
    }

    private boolean validSign(String requestBody, RequestBody body) {
        boolean hasSignType = RequestBody.SignType.MD5.getType().equals(body.getSignType());
        return hasSignType && StrUtil.equals(OpenwayUtils.getSign(requestBody), body.getSign());
    }

    private boolean validRequired(RequestBody body) {
        boolean hasMethod = StrUtil.isNotBlank(body.getMethod());
        boolean hasAppid = StrUtil.isNotBlank(body.getAppid());
        boolean hasSign = StrUtil.isNotBlank(body.getSign());
        boolean hasSignType = StrUtil.isNotBlank(body.getSignType());
        boolean hasTimestamp = ObjectUtil.isNotNull(body.getTimestamp());

        return hasMethod && hasAppid && hasSign && hasSignType && hasTimestamp;
    }

    private boolean validTimestamp(RequestBody body) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startAt = now.minusMinutes(5L);
        LocalDateTime endAt = now.plusMinutes(5L);
        LocalDateTime timestamp = body.getTimestamp();
        return endAt.isAfter(timestamp) && startAt.isBefore(timestamp);
    }
}
