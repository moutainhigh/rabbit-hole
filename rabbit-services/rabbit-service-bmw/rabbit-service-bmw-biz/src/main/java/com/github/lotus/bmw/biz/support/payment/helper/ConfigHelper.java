package com.github.lotus.bmw.biz.support.payment.helper;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.github.lotus.bmw.biz.entity.PaymentMch;
import com.github.lotus.bmw.biz.entity.PaymentMchAlipayConfig;
import com.github.lotus.bmw.biz.entity.PaymentMchWxpayConfig;
import com.github.lotus.bmw.biz.service.PaymentMchAlipayConfigService;
import com.github.lotus.bmw.biz.service.PaymentMchWxpayConfigService;
import com.github.lotus.common.datadict.bmw.PaymentMchType;
import com.github.lotus.common.utils.Rules;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.payment.ConfigStorage;
import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentService;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.wxpay.sign.WxSignType;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import in.hocg.payment.wxpay.v2.WxPayService;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ConfigHelper {

    public static ConfigStorage createConfigStorage(PaymentMch paymentMch) {
        Optional<ConfigStorage> opt = Rules.create()
            // 支付宝
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> createAlipayConfigStorage(paymentMch.getId())))
            // 微信
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> createWxpayConfigStorage(paymentMch.getId())))
            .of(ICode.ofThrow(paymentMch.getType(), PaymentMchType.class));
        return opt.orElseThrow(UnsupportedOperationException::new);
    }

    public static ConfigStorage createAlipayConfigStorage(Long paymentMchId) {
        PaymentMchAlipayConfig config = SpringContext.getBean(PaymentMchAlipayConfigService.class).getById(paymentMchId);
        return ConfigStorages.createConfigStorage(AliPayConfigStorage.class)
            .setAliPayPublicKey(config.getPublicKey())
            .setPrivateKey(config.getPrivateKey())
            .setAppId(config.getAppid())
            .setSignType(SignType.RSA2)
            .setIsDev(config.getIsDev());
    }

    public static ConfigStorage createWxpayConfigStorage(Long paymentMchId) {
        PaymentMchWxpayConfig config = SpringContext.getBean(PaymentMchWxpayConfigService.class).getById(paymentMchId);
        return ConfigStorages.createConfigStorage(WxPayConfigStorage.class)
            .setCertFileStr(config.getCertStr())
            .setMchId(config.getMchId())
            .setKey(config.getKeyStr())
            .setSignType(WxSignType.HMAC_SHA256)
            .setIsDev(false)
            .setAppId(config.getAppid());
    }
}
