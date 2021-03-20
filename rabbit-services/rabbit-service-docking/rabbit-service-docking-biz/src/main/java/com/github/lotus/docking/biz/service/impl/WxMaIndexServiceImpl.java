package com.github.lotus.docking.biz.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.github.lotus.docking.biz.cache.WxMaCacheService;
import com.github.lotus.docking.biz.pojo.dto.UserInfoDto;
import com.github.lotus.docking.biz.pojo.ro.GetMaUserToken2Ro;
import com.github.lotus.docking.biz.pojo.ro.GetMaUserTokenRo;
import com.github.lotus.docking.biz.pojo.vo.WxMaLoginVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaPhoneNumberInfoVo;
import com.github.lotus.docking.biz.service.WxMaIndexService;
import com.github.lotus.docking.biz.support.wxmini.WxMaConfiguration;
import com.github.lotus.ums.api.SocialServiceApi;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.constant.SocialType;
import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

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
    private final SocialServiceApi socialServiceApi;
    private final UserServiceApi accountServiceApi;
    private final WxMaCacheService wxMaCacheService;

    private WxMaLoginVo getOrCreateUserToken(String appid, UserInfoDto dto) {
        String code = dto.getCode();
        String nickName = dto.getNickName();
        String avatarUrl = dto.getAvatarUrl();

        WxMaLoginVo result = new WxMaLoginVo();
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        WxMaUserService userService = wxService.getUserService();

        try {
            WxMaJscode2SessionResult session = userService.getSessionInfo(code);
            String sessionKey = session.getSessionKey();
            String openid = session.getOpenid();
            log.debug("sessionkey: [{}]; openid: [{}]", sessionKey, openid);
            String socialType = (String) SocialType.WxMa.getCode();
            UserDetailVo userDetailVo = socialServiceApi.getAccountBySocialTypeAndSocialId(socialType, openid);

            // 如果没有关联则自动创建一个账号
            if (Objects.isNull(userDetailVo)) {
                CreateAccountRo createAccountRo = new CreateAccountRo()
                    .setAvatar(avatarUrl)
                    .setNickname(nickName)
                    .setSocials(Collections.singletonList(new CreateAccountRo.SocialItem()
                        .setSocialType(socialType)
                        .setSocialId(openid)));
                userDetailVo = accountServiceApi.createAccount(createAccountRo);
            }

            String username = userDetailVo.getUsername();
            wxMaCacheService.updateWxMaSessionUser(sessionKey, username);

            // 关联账号
            WxMaLoginVo.UserDetailVo userDetail = new WxMaLoginVo.UserDetailVo()
                .setAvatar(userDetailVo.getAvatarUrl())
                .setNickname(userDetailVo.getNickname())
                .setUsername(username)
                .setId(userDetailVo.getId());
            return result.setToken(accountServiceApi.getUserToken(username)).setUserDetail(userDetail);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw ServiceException.wrap(e);
        }
    }

    @Override
    public WxMaLoginVo getUserToken2(String appid, GetMaUserToken2Ro ro) {
        String code = ro.getCode();
        String avatarUrl = ro.getAvatarUrl();
        String nickName = ro.getNickName();

        UserInfoDto userInfoDto = new UserInfoDto().setCode(code)
            .setAvatarUrl(avatarUrl).setNickName(nickName);
        return this.getOrCreateUserToken(appid, userInfoDto);
    }

    @Override
    public WxMaLoginVo getUserToken(String appid, GetMaUserTokenRo ro) {
        String iv = ro.getIv();
        String encryptedData = ro.getEncryptedData();
        String signature = ro.getSignature();
        String rawData = ro.getRawData();
        String code = ro.getCode();

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        WxMaUserService userService = wxService.getUserService();

        try {
            WxMaJscode2SessionResult session = userService.getSessionInfo(code);
            String sessionKey = session.getSessionKey();
            String openid = session.getOpenid();
            log.debug("sessionkey: [{}]; openid: [{}]", sessionKey, openid);
            WxMaUserInfo userInfo = this.getUserInfo(appid, sessionKey, signature, rawData, encryptedData, iv);
            String avatarUrl = userInfo.getAvatarUrl();
            String nickName = userInfo.getNickName();

            UserInfoDto userInfoDto = new UserInfoDto().setCode(code)
                .setAvatarUrl(avatarUrl).setNickName(nickName);
            return this.getOrCreateUserToken(appid, userInfoDto);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw ServiceException.wrap(e);
        }
    }

    private WxMaUserInfo getUserInfo(String appid, String sessionKey,
                                     String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        WxMaUserService userService = wxService.getUserService();
        // 用户信息校验
        if (!userService.checkUserInfo(sessionKey, rawData, signature)) {
            ValidUtils.fail("user check failed");
        }

        // 解密用户信息
        return userService.getUserInfo(sessionKey, encryptedData, iv);
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
        return new WxMaPhoneNumberInfoVo()
            .setPurePhoneNumber(phoneNoInfo.getPurePhoneNumber())
            .setPhoneNumber(phoneNoInfo.getPhoneNumber())
            .setCountryCode(phoneNoInfo.getCountryCode());
    }

    @Override
    public boolean checkMessage(String appid, String text) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        try {
            return wxService.getSecCheckService().checkMessage(text);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw ServiceException.wrap(e);
        }
    }

    @Override
    public boolean checkImage(String appid, String imageUrl) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        try {
            return wxService.getSecCheckService().checkImage(imageUrl);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw ServiceException.wrap(e);
        }
    }
}
