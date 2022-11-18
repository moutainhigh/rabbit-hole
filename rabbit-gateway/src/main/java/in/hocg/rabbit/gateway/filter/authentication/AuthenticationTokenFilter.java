package in.hocg.rabbit.gateway.filter.authentication;

import in.hocg.boot.sso.client.autoconfigure.core.BearerTokenAuthentication;
import in.hocg.rabbit.gateway.service.UserService;
import in.hocg.rabbit.gateway.utils.CommonUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthenticationTokenFilter implements BearerTokenAuthentication {

    public static final String EXPIRED_TOKEN = ":expired_token";
    private final UserService userService;

    @Override
    public Authentication authentication(String token) {
        try {
            String username = userService.getUsername(token);
            return new UsernamePasswordAuthenticationToken(username, token, CommonUtils.asGrantedAuthority(userService.getAuthorities(username)));
        } catch (ExpiredJwtException e) {
            log.warn("Jwt Expired: {}", token);
            return new UsernamePasswordAuthenticationToken(EXPIRED_TOKEN, null, Collections.emptyList());
        } catch (Exception e) {
            log.warn("用户登陆鉴权失败 token=[{}], 失败: {}", token, e);
            return new UsernamePasswordAuthenticationToken("", token, Collections.emptyList());
        }
    }
}
