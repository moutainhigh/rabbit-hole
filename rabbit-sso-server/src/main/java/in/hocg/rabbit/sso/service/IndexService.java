package in.hocg.rabbit.sso.service;

import in.hocg.rabbit.docking.api.pojo.vo.WxMpQrCodeVo;
import in.hocg.rabbit.sso.pojo.vo.WxLoginStatusVo;
import in.hocg.rabbit.ums.api.pojo.ro.ForgotRo;
import in.hocg.rabbit.ums.api.pojo.ro.RegisterRo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface IndexService {

    WxMpQrCodeVo getWechatQrcode();

    WxLoginStatusVo getWechatQrcodeStatus(String idFlag, String redirectUrl);

    void forgot(ForgotRo ro);

    void register(RegisterRo ro);
}
