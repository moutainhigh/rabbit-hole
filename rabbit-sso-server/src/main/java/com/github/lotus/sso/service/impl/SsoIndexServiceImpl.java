package com.github.lotus.sso.service.impl;

import com.github.lotus.chaos.api.modules.lang.SmsApi;
import com.github.lotus.chaos.api.modules.ums.AccountApi;
import com.github.lotus.chaos.api.modules.ums.constant.SocialType;
import com.github.lotus.chaos.api.modules.ums.pojo.ro.CreateAccountRo;
import com.github.lotus.docking.api.WxApi;
import com.github.lotus.docking.api.pojo.vo.WxLoginInfoVo;
import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import com.github.lotus.sso.config.security.PageConstants;
import com.github.lotus.sso.config.security.SecurityContext;
import com.github.lotus.sso.mapstruct.AccountMapping;
import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.sso.pojo.ro.LoginRo;
import com.github.lotus.sso.pojo.ro.SendSmsCodeRo;
import com.github.lotus.sso.pojo.vo.WxLoginStatusVo;
import com.github.lotus.sso.service.SocialService;
import com.github.lotus.sso.service.SsoIndexService;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.boot.web.servlet.SpringServletContext;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SsoIndexServiceImpl implements SsoIndexService {
    private final AccountApi accountApi;
    private final WxApi wxApi;
    private final SmsApi smsApi;
    private final AccountMapping mapping;
    private final PasswordEncoder passwordEncoder;
    private final SocialService socialService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    @Value("${rabbit.wx.use}")
    private String useWxAppid;


    @Override
    public String getUserToken(String username) {
        return accountApi.getToken(username);
    }

    @Override
    public void createAccount(JoinRo ro) {
        ro.setPassword(passwordEncoder.encode(ro.getPassword()));
        CreateAccountRo createAccountRo = mapping.asCreateAccountRo(ro);
        createAccountRo.setCreatedIp(SpringServletContext.getClientIp().orElse(null));
        accountApi.createAccount(createAccountRo);
    }

    @Override
    public void sendSmsCode(SendSmsCodeRo ro) {
        smsApi.sendSmsCode(ro.getPhone());
    }

    @Override
    public WxMpQrCodeVo getWxQrCode() {
        return wxApi.getQrCode(useWxAppid);
    }

    @Override
    public WxLoginStatusVo getWxLoginStatus(String idFlag, String redirectUrl) {
        String socialType = (String) SocialType.WxMp.getCode();
        WxLoginInfoVo wxLoginStatus = wxApi.getWxLoginStatus(idFlag);
        String socialId = wxLoginStatus.getOpenid();
        WxLoginInfoVo.WxLoginStatus status = wxLoginStatus.getStatus();
        String username = SecurityContext.getCurrentUsername().orElse(null);

        WxLoginStatusVo result = new WxLoginStatusVo();
        result.setStatus((String) status.getCode());

        if (WxLoginInfoVo.WxLoginStatus.Success.equals(status)) {
            socialService.onAuthenticationSuccess(socialType, socialId, username);
            result.setRedirectUrl(Strings.isBlank(redirectUrl) ? PageConstants.INDEX_PAGE : redirectUrl);
        }
        return result;
    }

    @Override
    public String login(LoginRo ro) {
        String username = ro.getUsername();
        String password = ro.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw ServiceException.wrap("用户名或密码错误");
        }
        return accountApi.getToken(username);
    }
}
