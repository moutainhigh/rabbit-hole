package com.github.lotus.pay.biz.support.payment;

import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.wxpay.sign.WxSignType;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import in.hocg.payment.wxpay.v2.WxPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Created by hocgin on 2021/1/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Configuration
@EnableConfigurationProperties({PaymentProperties.class})
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentConfiguration {
    private final PaymentProperties properties;

    @Bean
    AliPayService aliPayService() {
        final AliPayConfigStorage configStorage = ConfigStorages.createConfigStorage(AliPayConfigStorage.class)
            .setAliPayPublicKey(properties.getAliPayPublicKey())
            .setPrivateKey(properties.getAliPayPrivateKey())
            .setAppId(properties.getAliPayAppId())
            .setSignType(SignType.RSA2)
            .setIsDev(true);
        return PaymentServices.createPaymentService(AliPayService.class, configStorage);
    }

    @Bean
    WxPayService wxPayService() {
        final WxPayConfigStorage configStorage = ConfigStorages.createConfigStorage(WxPayConfigStorage.class)
//            .setAppId(properties.getWxAppId())
//            .setKey(properties.getWxKey())
//            .setMchId(properties.getWxMchId())
//            .setCertFile(new File(properties.getWxCertFile()))
            .setSignType(WxSignType.HMAC_SHA256)
            .setIsDev(false);
        return PaymentServices.createPaymentService(WxPayService.class, configStorage);
    }
}
