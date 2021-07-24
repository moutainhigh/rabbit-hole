package com.github.lotus.chaos.biz.service;


import com.github.lotus.chaos.biz.pojo.ro.SendEmailCodeRo;
import com.github.lotus.chaos.biz.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.biz.pojo.vo.IpAddressVo;

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
