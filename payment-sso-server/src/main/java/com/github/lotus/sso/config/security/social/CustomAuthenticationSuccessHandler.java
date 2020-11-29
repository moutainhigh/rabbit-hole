package com.github.lotus.sso.config.security.social;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User principal = (CustomOAuth2User) authentication.getPrincipal();
        String principalName = principal.getName();

        Object account = null; // 查找数据库

        if (Objects.isNull(account)) {
            log.debug("==> 注册: [{}]", principalName);
        } else {
            log.debug("==> 登陆/绑定账户: [{}]", principalName);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
