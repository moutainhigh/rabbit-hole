package com.github.lotus.sso.config.security.user;

import com.github.lotus.sso.config.security.AuthorizedSuccessResult;
import com.github.lotus.sso.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hocgin on 2020/1/7.
 * email: hocgin@gmail.com
 * 登录验证成功
 *
 * @author hocgin
 */
@Slf4j
public class AuthorizedSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler {

    public AuthorizedSuccessHandle(String loginSuccessPage) {
        setAlwaysUseDefaultTargetUrl(false);
        setDefaultTargetUrl(loginSuccessPage);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (IsAjaxRequestMatcher.THIS.matches(request)) {
            handleAjaxRequest(response, request);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    private void handleAjaxRequest(HttpServletResponse response, HttpServletRequest request) throws IOException {
        log.debug("使用 AJAX 方式登录成功");

        String redirectUrl = getRedirectUrl(request);

        AuthorizedSuccessResult result = AuthorizedSuccessResult.create(redirectUrl);
        ResponseUtils.setUtf8(response);
        response.setStatus(HttpServletResponse.SC_OK);
        try (final PrintWriter writer = response.getWriter()) {
            writer.write(result.toJSON());
        }
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String xPageUrl = request.getHeader("X-Page-Url");
        String referer = request.getHeader("Referer");

        String targetUrl = xPageUrl;
        if (StringUtils.isEmpty(xPageUrl)) {
            targetUrl = referer;
        }
        return targetUrl;
    }
}
