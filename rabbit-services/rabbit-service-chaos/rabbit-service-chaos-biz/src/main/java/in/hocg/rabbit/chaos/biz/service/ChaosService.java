package in.hocg.rabbit.chaos.biz.service;


import in.hocg.rabbit.chaos.biz.pojo.ro.SendEmailCodeRo;
import in.hocg.rabbit.chaos.biz.pojo.ro.SendSmsCodeRo;
import in.hocg.rabbit.chaos.biz.pojo.vo.IpAddressVo;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ChaosService {

    Long sendSmsCode(SendSmsCodeRo ro);

    IpAddressVo getAddress4ip(String ip);

    void sendEmailCode(SendEmailCodeRo ro);

    String encrypt(String data);
}
