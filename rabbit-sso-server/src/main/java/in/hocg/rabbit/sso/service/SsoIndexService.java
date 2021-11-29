package in.hocg.rabbit.sso.service;

import in.hocg.rabbit.docking.api.pojo.vo.WxMpQrCodeVo;
import in.hocg.rabbit.sso.pojo.ro.JoinRo;
import in.hocg.rabbit.sso.pojo.vo.WxLoginStatusVo;

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
