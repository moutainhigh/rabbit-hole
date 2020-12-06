package com.github.lotus.docking.biz.service;

import com.github.lotus.docking.api.pojo.vo.WxLoginInfoVo;
import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface WxMpIndexService {

    WxMpQrCodeVo getWxMpQrcodeUrl(String appid);

    WxLoginInfoVo getWxLoginStatus(String idFlag);

    /**
     * 处理微信公众号订阅登陆
     *
     * @param appid
     * @param fromUser
     * @param scene
     */
    void handleWxMpLoginOnSubscription(String appid, String fromUser, String scene);
}
