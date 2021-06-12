package com.github.lotus.sso.config.security.support.mobile;

import com.github.lotus.sso.config.security.helper.PageConstants;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hocgin on 2021/6/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final boolean postOnly = true;
    @Setter
    private AuthenticationEntryPoint authenticationEntryPoint;

    public MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher(PageConstants.MOBILE_TOKEN_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException(
                "Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);

        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim();

        MobileAuthenticationToken mobileAuthenticationToken = new MobileAuthenticationToken(mobile);

        setDetails(request, mobileAuthenticationToken);

        Authentication authResult = null;
        try {
            authResult = this.getAuthenticationManager().authenticate(mobileAuthenticationToken);

            logger.debug("Authentication success: " + authResult);
            SecurityContextHolder.getContext().setAuthentication(authResult);

        } catch (Exception failed) {
            SecurityContextHolder.clearContext();
            logger.debug("Authentication request failed: " + failed);

            try {
                authenticationEntryPoint.commence(request, response,
                    new UsernameNotFoundException(failed.getMessage(), failed));
            } catch (Exception e) {
                logger.error("authenticationEntryPoint handle error:{}", failed);
            }
        }

        return authResult;
    }

    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter("mobile");
    }

    private void setDetails(HttpServletRequest request,
                            MobileAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
