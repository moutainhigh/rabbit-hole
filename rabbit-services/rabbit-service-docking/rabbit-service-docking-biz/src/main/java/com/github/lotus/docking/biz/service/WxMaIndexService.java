package com.github.lotus.docking.biz.service;

import com.github.lotus.docking.biz.pojo.ro.GetMaUserTokenRo;
import com.github.lotus.docking.biz.pojo.vo.WxMaLoginVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaPhoneNumberInfoVo;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface WxMaIndexService {

    /**
     * 获取账号 Token 如果没有账号会自动创建
     *
     * @param appid
     * @param ro
     * @return
     */
    WxMaLoginVo getUserToken(String appid, GetMaUserTokenRo ro);

    WxMaPhoneNumberInfoVo getUserPhone(String appid, String sessionKey, String signature, String rawData, String encryptedData, String iv);

    boolean checkMessage(String appid, String text);

    boolean checkImage(String appid, String imageUrl);
}
