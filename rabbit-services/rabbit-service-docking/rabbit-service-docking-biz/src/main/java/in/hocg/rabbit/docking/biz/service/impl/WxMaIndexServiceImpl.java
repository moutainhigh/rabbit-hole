package in.hocg.rabbit.docking.biz.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import in.hocg.rabbit.docking.biz.cache.WxMaCacheService;
import in.hocg.rabbit.docking.biz.pojo.dto.UserInfoDto;
import in.hocg.rabbit.docking.biz.pojo.ro.GetMaUserToken2Ro;
import in.hocg.rabbit.docking.biz.pojo.ro.GetMaUserTokenRo;
import in.hocg.rabbit.docking.biz.pojo.vo.WxMaLoginVo;
import in.hocg.rabbit.docking.biz.pojo.vo.WxMaPhoneNumberInfoVo;
import in.hocg.rabbit.docking.biz.service.WxMaIndexService;
import in.hocg.rabbit.ums.api.SocialServiceApi;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.enums.SocialType;
import in.hocg.rabbit.ums.api.pojo.ro.CreateAccountRo;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final WxMaService wxMaService;

    private WxMaLoginVo getOrCreateUserToken(String appid, UserInfoDto dto) {
        String sessionKey = dto.getSessionKey();
        String openid = dto.getOpenid();
        String nickName = dto.getNickName();
        String avatarUrl = dto.getAvatarUrl();
        log.debug("sessionkey: [{}]; openid: [{}]", sessionKey, openid);

        WxMaLoginVo result = new WxMaLoginVo();
        String socialType = (String) SocialType.WxMa.getCode();
        UserDetailVo userDetailVo = socialServiceApi.getAccountBySocialTypeAndSocialId(socialType, openid);

        // ?????????????????????????????????????????????
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

        // ????????????
        WxMaLoginVo.UserDetailVo userDetail = new WxMaLoginVo.UserDetailVo()
            .setAvatar(userDetailVo.getAvatarUrl())
            .setNickname(userDetailVo.getNickname())
            .setUsername(username)
            .setId(userDetailVo.getId());
        return result.setToken(accountServiceApi.getUserToken(username)).setUserDetail(userDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxMaLoginVo getUserToken2(String appid, GetMaUserToken2Ro ro) {
        String code = ro.getCode();
        String avatarUrl = ro.getAvatarUrl();
        String nickName = ro.getNickName();

        final WxMaService wxService = wxMaService.switchoverTo(appid);
        WxMaUserService userService = wxService.getUserService();

        String sessionKey;
        String openid;
        try {
            WxMaJscode2SessionResult session = userService.getSessionInfo(code);
            sessionKey = session.getSessionKey();
            openid = session.getOpenid();
        } catch (Exception e) {
            throw ServiceException.wrap(e);
        }

        UserInfoDto userInfoDto = new UserInfoDto()
            .setSessionKey(sessionKey).setOpenid(openid)
            .setAvatarUrl(avatarUrl).setNickName(nickName);
        return this.getOrCreateUserToken(appid, userInfoDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxMaLoginVo getUserToken(String appid, GetMaUserTokenRo ro) {
        String iv = ro.getIv();
        String encryptedData = ro.getEncryptedData();
        String signature = ro.getSignature();
        String rawData = ro.getRawData();
        String code = ro.getCode();

        final WxMaService wxService = wxMaService.switchoverTo(appid);
        WxMaUserService userService = wxService.getUserService();

        String sessionKey;
        String openid;
        try {
            WxMaJscode2SessionResult session = userService.getSessionInfo(code);
            sessionKey = session.getSessionKey();
            openid = session.getOpenid();
        } catch (Exception e) {
            throw ServiceException.wrap(e);
        }

        WxMaUserInfo userInfo = this.getUserInfo(appid, sessionKey, signature, rawData, encryptedData, iv);
        String avatarUrl = userInfo.getAvatarUrl();
        String nickName = userInfo.getNickName();
        UserInfoDto userInfoDto = new UserInfoDto()
            .setOpenid(openid).setSessionKey(sessionKey)
            .setAvatarUrl(avatarUrl).setNickName(nickName);
        return this.getOrCreateUserToken(appid, userInfoDto);
    }

    private WxMaUserInfo getUserInfo(String appid, String sessionKey,
                                     String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = wxMaService.switchoverTo(appid);

        WxMaUserService userService = wxService.getUserService();
        // ??????????????????
        if (!userService.checkUserInfo(sessionKey, rawData, signature)) {
            ValidUtils.fail("user check failed");
        }

        // ??????????????????
        return userService.getUserInfo(sessionKey, encryptedData, iv);
    }

    @Override
    public WxMaPhoneNumberInfoVo getUserPhone(String appid, String sessionKey,
                                              String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = wxMaService.switchoverTo(appid);

        // ??????????????????
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            ValidUtils.fail("user check failed");
        }

        // ??????
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        return new WxMaPhoneNumberInfoVo()
            .setPurePhoneNumber(phoneNoInfo.getPurePhoneNumber())
            .setPhoneNumber(phoneNoInfo.getPhoneNumber())
            .setCountryCode(phoneNoInfo.getCountryCode());
    }

    @Override
    public boolean checkMessage(String appid, String text) {
        final WxMaService wxService = wxMaService.switchoverTo(appid);
        try {
            return wxService.getSecCheckService().checkMessage(text);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw ServiceException.wrap(e);
        }
    }

    @Override
    public boolean checkImage(String appid, String imageUrl) {
        final WxMaService wxService = wxMaService.switchoverTo(appid);
        try {
            return wxService.getSecCheckService().checkImage(imageUrl);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw ServiceException.wrap(e);
        }
    }
}
