package in.hocg.rabbit.sso.service.impl;

import in.hocg.rabbit.docking.api.WxServiceApi;
import in.hocg.rabbit.docking.api.pojo.vo.WxLoginInfoVo;
import in.hocg.rabbit.docking.api.pojo.vo.WxMpQrCodeVo;
import in.hocg.rabbit.sso.config.security.helper.PageConstants;
import in.hocg.rabbit.sso.config.security.helper.SecurityContext;
import in.hocg.rabbit.sso.pojo.vo.WxLoginStatusVo;
import in.hocg.rabbit.sso.service.SocialService;
import in.hocg.rabbit.sso.service.IndexService;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.constant.SocialType;
import in.hocg.rabbit.ums.api.pojo.ro.ForgotRo;
import in.hocg.rabbit.ums.api.pojo.ro.RegisterRo;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndexServiceImpl implements IndexService {
    private final WxServiceApi wxServiceApi;
    private final SocialService socialService;
    private final UserServiceApi userServiceApi;

    @Override
    public WxMpQrCodeVo getWechatQrcode() {
        return wxServiceApi.getQrCode("wxb4df1f0c3dc96256");
    }

    @Override
    public WxLoginStatusVo getWechatQrcodeStatus(String idFlag, String redirectUrl) {
        String socialType = SocialType.WxMp.getCode();
        WxLoginInfoVo wxLoginStatus = wxServiceApi.getWxLoginStatus(idFlag);
        String socialId = wxLoginStatus.getOpenid();
        WxLoginInfoVo.WxLoginStatus status = wxLoginStatus.getStatus();
        String username = SecurityContext.getCurrentUsername().orElse(null);

        WxLoginStatusVo result = new WxLoginStatusVo();
        result.setStatus(status.getCode());

        if (WxLoginInfoVo.WxLoginStatus.Success.equals(status)) {
            socialService.onAuthenticationSuccess(socialType, socialId, username);
            result.setRedirectUrl(Strings.isBlank(redirectUrl) ? PageConstants.INDEX_PAGE : redirectUrl);
        }
        return result;
    }

    @Override
    public void forgot(ForgotRo ro) {
        userServiceApi.forgot(ro);
    }

    @Override
    public void register(RegisterRo ro) {
        userServiceApi.register(ro);
    }

}
