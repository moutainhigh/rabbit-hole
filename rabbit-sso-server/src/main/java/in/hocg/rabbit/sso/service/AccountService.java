package in.hocg.rabbit.sso.service;

import in.hocg.rabbit.sso.pojo.ro.ConfirmQrcodeRo;
import in.hocg.rabbit.sso.pojo.ro.JoinAccountRo;
import in.hocg.rabbit.sso.pojo.ro.LoginRo;
import in.hocg.rabbit.ums.api.pojo.vo.GetLoginQrcodeVo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface AccountService {

    String login(LoginRo ro);

    String join(JoinAccountRo ro);

    GetLoginQrcodeVo getLoginQrcode();

    void confirmQrcode(ConfirmQrcodeRo ro);
}
