package in.hocg.rabbit.sso.service.impl;

import in.hocg.rabbit.docking.api.WxServiceApi;
import in.hocg.rabbit.docking.api.pojo.vo.WxLoginInfoVo;
import in.hocg.rabbit.docking.api.pojo.vo.WxMpQrCodeVo;
import in.hocg.rabbit.sso.config.security.helper.PageConstants;
import in.hocg.rabbit.sso.config.security.helper.SecurityContext;
import in.hocg.rabbit.sso.mapstruct.AccountMapping;
import in.hocg.rabbit.sso.pojo.ro.JoinRo;
import in.hocg.rabbit.sso.pojo.vo.WxLoginStatusVo;
import in.hocg.rabbit.sso.service.SocialService;
import in.hocg.rabbit.sso.service.SsoIndexService;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.constant.SocialType;
import in.hocg.rabbit.ums.api.pojo.ro.CreateAccountRo;
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
