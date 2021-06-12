package com.github.lotus.sso.service.impl;

import com.github.lotus.chaos.api.SmsServiceApi;
import com.github.lotus.docking.api.WxServiceApi;
import com.github.lotus.docking.api.pojo.vo.WxLoginInfoVo;
import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import com.github.lotus.sso.config.security.PageConstants;
import com.github.lotus.sso.config.security.SecurityContext;
import com.github.lotus.sso.mapstruct.AccountMapping;
import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.sso.pojo.ro.SendSmsCodeRo;
import com.github.lotus.sso.pojo.vo.WxLoginStatusVo;
import com.github.lotus.sso.service.SocialService;
import com.github.lotus.sso.service.SsoIndexService;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.constant.SocialType;
import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
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
    private final UserServiceApi userServiceApi;
    private final WxServiceApi wxServiceApi;
    private final SmsServiceApi smsServiceApi;
    private final AccountMapping mapping;
    private final PasswordEncoder passwordEncoder;
    private final SocialService socialService;

    @Override
    public void createAccount(JoinRo ro) {
        ro.setPassword(passwordEncoder.encode(ro.getPassword()));
        CreateAccountRo createAccountRo = mapping.asCreateAccountRo(ro);
        createAccountRo.setCreatedIp(SpringServletContext.getClientIp().orElse(null));
        userServiceApi.createAccount(createAccountRo);
    }

    @Override
    public void sendSmsCode(SendSmsCodeRo ro) {
        smsServiceApi.sendVerifyCode(ro.getPhone());
    }

    @Override
    public WxMpQrCodeVo getWxQrCode(String appid) {
        return wxServiceApi.getQrCode(appid);
    }

    @Override
    public WxLoginStatusVo getWxLoginStatus(String idFlag, String redirectUrl) {
        String socialType = (String) SocialType.WxMp.getCode();
        WxLoginInfoVo wxLoginStatus = wxServiceApi.getWxLoginStatus(idFlag);
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
}
