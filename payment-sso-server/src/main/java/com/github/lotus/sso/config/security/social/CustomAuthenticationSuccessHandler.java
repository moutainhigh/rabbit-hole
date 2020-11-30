package com.github.lotus.sso.config.security.social;

import com.github.lotus.chaos.modules.ums.api.AccountApi;
import com.github.lotus.chaos.modules.ums.api.SocialApi;
import com.github.lotus.chaos.modules.ums.ro.InsertSocialRo;
import com.github.lotus.chaos.modules.ums.vo.UserDetailVo;
import com.github.lotus.sso.config.security.PageConstants;
import com.github.lotus.sso.config.security.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
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
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final SocialApi socialApi;
    private final AccountApi accountApi;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User principal = (CustomOAuth2User) authentication.getPrincipal();
        String principalName = principal.getName();
        String registrationId = principal.getRegistrationId();
        String username = principal.getUsername();

        UserDetailVo userDetail = socialApi.getAccountBySocialTypeAndSocialId(registrationId, principalName);

        // 社交账号未被绑定
        if (Objects.isNull(userDetail)) {
            // 已有用户登陆
            if (Objects.nonNull(username)) {
                // 这个用户需要未绑定改类型的社交账号
                log.debug("==> 绑定社交账号 [{}] 到 [{}]", registrationId + principalName, username);
                UserDetailVo user = accountApi.getUser(username);
                socialApi.insertOne(new InsertSocialRo()
                    .setSocialType(registrationId)
                    .setSocialId(principalName)
                    .setAccountId(user.getId()));
            }
            // 未有用户登陆
            else {
                // 退出社交账号登陆
                log.debug("==> 使用社交账号 [{}] 去注册 or 跳转注册页面", registrationId + principalName);
                SecurityContext.signOut();
                response.sendRedirect(PageConstants.SIGN_UP_PAGE);
                return;
            }
        }
        // 社交账号已被绑定
        else {
            log.debug("==> 登陆社交账户(替换为Username登陆): [{}]", registrationId + principalName);
        }

        SecurityContext.signIn(userDetail.getUsername());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
