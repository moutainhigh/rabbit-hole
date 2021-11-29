package in.hocg.rabbit.docking.biz.service;

import in.hocg.rabbit.docking.biz.pojo.ro.GetMaUserToken2Ro;
import in.hocg.rabbit.docking.biz.pojo.ro.GetMaUserTokenRo;
import in.hocg.rabbit.docking.biz.pojo.vo.WxMaLoginVo;
import in.hocg.rabbit.docking.biz.pojo.vo.WxMaPhoneNumberInfoVo;
import io.swagger.annotations.ApiOperation;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface WxMaIndexService {

    WxMaLoginVo getUserToken2(String appid, GetMaUserToken2Ro ro);

    @ApiOperation("获取账号 Token 如果没有账号会自动创建")
    WxMaLoginVo getUserToken(String appid, GetMaUserTokenRo ro);

    WxMaPhoneNumberInfoVo getUserPhone(String appid, String sessionKey, String signature, String rawData, String encryptedData, String iv);

    boolean checkMessage(String appid, String text);

    boolean checkImage(String appid, String imageUrl);
}
