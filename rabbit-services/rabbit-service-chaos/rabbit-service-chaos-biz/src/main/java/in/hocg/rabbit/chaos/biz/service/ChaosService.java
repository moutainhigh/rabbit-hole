package in.hocg.rabbit.chaos.biz.service;


import in.hocg.rabbit.chaos.api.pojo.ro.SendVerifyCodeRo;
import in.hocg.rabbit.chaos.api.pojo.vo.ValidVerifyCodeVo;
import in.hocg.rabbit.chaos.api.pojo.vo.GetVerifyCodeVo;
import in.hocg.rabbit.chaos.biz.pojo.vo.IpAddressVo;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ChaosService {

    IpAddressVo getAddress4ip(String ip);

    String encrypt(String data);

    GetVerifyCodeVo sendVerifyCode(SendVerifyCodeRo ro);

    ValidVerifyCodeVo validVerifyCode(String serialNo, String verifyCode);
}
