package in.hocg.rabbit.openway.basic.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import in.hocg.rabbit.openway.basic.context.GatewayContext;
import in.hocg.rabbit.openway.basic.route.RouteService;
import in.hocg.rabbit.openway.basic.data.RequestBody;
import in.hocg.rabbit.openway.constants.OrderedConstants;
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
@Order(OrderedConstants.VALID_REQUEST_ORDERED)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ValidRequestFilter implements WebFilter {
    private final RouteService routeService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.debug("ValidRequestFilter");
        GatewayContext context = exchange.getAttribute(GatewayContext.NAME);
        assert context != null;
        String requestBody = context.getBody();
        RequestBody body = context.getRequestBody();
        String method = body.getMethod();

        // 校验必填字段
        if (!validRequired(body)) {
            return ExceptionUtils.handleException(exchange, method, new RuntimeException("必填参数不能为空"));
        }

        // 校验时间戳
        if (!validTimestamp(body)) {
            return ExceptionUtils.handleException(exchange, method, new RuntimeException("时间戳校验失败"));
        }

        // 校验接口权限
        if (!validAllow(body)) {
            return ExceptionUtils.handleException(exchange, method, new RuntimeException("接口不允许访问"));
        }

        // 校验签名
        if (!validSign(requestBody, body)) {
            return ExceptionUtils.handleException(exchange, method, new RuntimeException("签名校验失败"));
        }

        log.debug("ValidRequestFilter success");
        return chain.filter(exchange);
    }

    private boolean validAllow(RequestBody body) {
        return routeService.hasAuthority(body.getAppid(), body.getMethod());
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
