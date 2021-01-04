package com.github.lotus.chaos.biz.modules.lang.service;


import com.github.lotus.chaos.biz.modules.lang.pojo.ro.SendEmailCodeRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.vo.IpAddressVo;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ChaosService {

    void sendSmsCode(SendSmsCodeRo ro);

    IpAddressVo getAddress4ip(String ip);

    void sendEmailCode(SendEmailCodeRo ro);

    String encrypt(String data);
}
