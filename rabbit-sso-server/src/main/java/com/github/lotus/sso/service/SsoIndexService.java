package com.github.lotus.sso.service;

import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import com.github.lotus.sso.pojo.ro.JoinRo;
import com.github.lotus.ums.api.pojo.vo.GetLoginQrcodeVo;
import com.github.lotus.sso.pojo.vo.WxLoginStatusVo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SsoIndexService {

    void createAccount(JoinRo ro);

    WxMpQrCodeVo getWxQrCode(String appid);

    WxLoginStatusVo getWxLoginStatus(String idFlag, String redirectUrl);

}
