package com.github.lotus.docking.biz.service;

import com.github.lotus.docking.biz.pojo.vo.WxMaLoginVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaPhoneNumberInfoVo;
import com.github.lotus.docking.biz.pojo.vo.WxMaUserInfoVo;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface WxMaIndexService {

    WxMaLoginVo login(String appid, String code);

    WxMaUserInfoVo getUserInfo(String appid, String sessionKey, String signature, String rawData, String encryptedData, String iv);

    WxMaPhoneNumberInfoVo getUserPhone(String appid, String sessionKey, String signature, String rawData, String encryptedData, String iv);

}
