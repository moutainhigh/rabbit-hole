package in.hocg.rabbit.gateway.filter.authorize;

import in.hocg.rabbit.gateway.filter.authentication.DefaultUserDetailsChecker;
import in.hocg.rabbit.gateway.service.UserService;
import in.hocg.rabbit.gateway.utils.ResultUtils;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import in.hocg.rabbit.usercontext.basic.HeaderConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

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
    private final UserDetailsChecker checker = new DefaultUserDetailsChecker();

    public AuthorizeFilter(AuthorizeProperties properties, UserService userService) {
        super(properties);
        this.userService = userService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();

        // 如果不需要认证
        if (isPermitAllWithIp(request) || isPermitAllWithUri(request)) {
            log.debug("无需认证, 访问URL=[{}]", uri);
            return chain.filter(exchange);
        }

        String username = request.getHeaders().getFirst(HeaderConstants.USERNAME);

        if (Strings.isBlank(username)) {
            log.warn("未登陆, 访问URL=[{}]", uri);
            return ResultUtils.notLogin(exchange);
        }

        try {
            checker.check(this.convertUser(userService.getUserDetail(username)));
        } catch (Exception e) {
            log.warn("检查账户发生异常: ", e);
            return ResultUtils.handleException(exchange, e);
        }

//        todo 如果要开启，需要给每个用户配置接口权限
//        if (!isPassAuthorize(request, username) && !RabbitUtils.isSuperAdmin(username)) {
//            log.warn("username:[{}], 访问URL=[{}]", username, uri);
//            return ResultUtils.accessDenied(exchange);
//        }

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

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 3;
    }

    private User convertUser(UserDetailVo userDetail) {
        return new User(userDetail.getUsername(), userDetail.getPassword(), userDetail.getEnabled(),
            userDetail.getExpired(), true, userDetail.getLocked(), Collections.emptyList());
    }
}
