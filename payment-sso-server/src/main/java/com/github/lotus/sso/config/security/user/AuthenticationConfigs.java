package com.github.lotus.sso.config.security.user;

import com.github.lotus.sso.config.security.social.CustomAuthenticationSuccessHandler;
import com.github.lotus.sso.config.security.social.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Created by hocgin on 2020/1/8.
 * email: hocgin@gmail.com
 * 配置
 *
 * @author hocgin
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthenticationConfigs {
    static final String LOGIN_SUCCESS_PAGE = "/index";
    static final String LOGIN_PAGE = "/login";
    private final CustomOAuth2UserService oAuth2UserService;
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    public void configure(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        final AuthorizedSuccessHandle successHandler = new AuthorizedSuccessHandle(LOGIN_SUCCESS_PAGE);
        final AuthorizedFailureHandle failureHandle = new AuthorizedFailureHandle(LOGIN_PAGE);

        // ==== OAuth2.0 ====
        http.oauth2Client();
        http.oauth2Login().loginPage(LOGIN_PAGE)
            .userInfoEndpoint().userService(oAuth2UserService).and()
            .successHandler(authenticationSuccessHandler);

        // ==== Form 表单 ====
        {
            http.formLogin().loginPage(LOGIN_PAGE)
                .successHandler(successHandler)
                .failureHandler(failureHandle)
                .permitAll();
        }

        // ==== 社交登陆 ====
    }

}
