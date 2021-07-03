package com.github.lotus.gateway.filter.authentication;

import com.github.lotus.common.utils.JwtUtils;
import com.github.lotus.gateway.service.UserService;
import in.hocg.boot.sso.client.autoconfigure.core.BearerTokenAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsChecker;
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
    private final UserService userService;
    private final UserDetailsChecker checker = new DefaultUserDetailsChecker();

    @Override
    public Authentication authentication(String token) {
        String username;
        try {
            username = JwtUtils.decode(token);
//            checker.check(userService.getUserDetail(username));
        } catch (Exception e) {
            log.warn("用户登陆鉴权失败 token=[{}], 失败: {}", token, e);
            return new UsernamePasswordAuthenticationToken("", null, Collections.emptyList());
        }
        return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
    }
}
