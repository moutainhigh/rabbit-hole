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

    WxMaLoginVo getUserToken(String appid, GetMaUserTokenRo ro);

    WxMaPhoneNumberInfoVo getUserPhone(String appid, String sessionKey, String signature, String rawData, String encryptedData, String iv);

}
