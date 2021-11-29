package in.hocg.rabbit.chaos.biz.support.sms;


import in.hocg.rabbit.chaos.biz.pojo.ro.SendSmsCodeRo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SmsService {

    Long sendSmsCode(SendSmsCodeRo qo);

    boolean validSmsCode(String phone, String smsCode);
}
