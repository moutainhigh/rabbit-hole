package com.github.lotus.sso.config.security.user;

import cn.hutool.json.JSONUtil;
import com.github.lotus.sso.utils.ResponseUtils;
import in.hocg.boot.web.result.ExceptionResult;
import in.hocg.boot.web.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
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
            handleAjaxRequest(response);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    private void handleAjaxRequest(HttpServletResponse response) throws IOException {
        log.info("登录验证成功");
        final ResultCode resultCode = ResultCode.SUCCESS;
        ExceptionResult result = ExceptionResult.create(HttpServletResponse.SC_OK, resultCode.getMessage());
        ResponseUtils.setUtf8(response);
        response.setStatus(HttpServletResponse.SC_OK);
        try (final PrintWriter writer = response.getWriter()) {
            writer.write(JSONUtil.toJsonStr(result));
        }
    }
}
