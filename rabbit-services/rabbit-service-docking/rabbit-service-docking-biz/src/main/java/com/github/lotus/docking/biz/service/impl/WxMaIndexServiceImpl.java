package com.github.lotus.docking.biz.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.github.lotus.docking.biz.pojo.vo.WxMaLoginVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaPhoneNumberInfoVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaUserInfoVo;
import com.github.lotus.docking.biz.service.WxMaIndexService;
import com.github.lotus.docking.biz.support.wxmini.WxMaConfiguration;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMaIndexServiceImpl implements WxMaIndexService {

    @Override
    public WxMaLoginVo login(String appid, String code) {
        ValidUtils.notNull(code, "参数错误");

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            String sessionKey = session.getSessionKey();
            String openid = session.getOpenid();
            log.debug("sessionkey: [{}]; openid: [{}]", sessionKey, openid);
            // 关联账号
            return new WxMaLoginVo();
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw ServiceException.wrap(e);
        }
    }

    @Override
    public WxMaUserInfoVo getUserInfo(String appid, String sessionKey,
                                      String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            ValidUtils.fail("user check failed");
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        return new WxMaUserInfoVo();
    }

    @Override
    public WxMaPhoneNumberInfoVo getUserPhone(String appid, String sessionKey,
                                              String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            ValidUtils.fail("user check failed");
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        return new WxMaPhoneNumberInfoVo();
    }
}
