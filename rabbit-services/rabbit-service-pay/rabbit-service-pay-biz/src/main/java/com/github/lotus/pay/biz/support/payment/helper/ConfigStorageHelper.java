package com.github.lotus.pay.biz.support.payment.helper;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.payment.ConfigStorage;
import in.hocg.payment.ConfigStorages;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.wxpay.sign.WxSignType;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ConfigStorageHelper {

    public static ConfigStorage createConfigStorage(Model<?> model) {
        if (model instanceof PlatformAlipayConfig) {
            return ConfigStorageHelper.createConfigStorage(model);
        } else if (model instanceof PlatformWxpayConfig) {
            return ConfigStorageHelper.createConfigStorage(model);
        }
        throw new UnsupportedOperationException();
    }

    public static ConfigStorage createConfigStorage(PlatformAlipayConfig config) {
        ValidUtils.notNull(config);
        return ConfigStorages.createConfigStorage(AliPayConfigStorage.class)
            .setAliPayPublicKey(config.getPublicKey())
            .setPrivateKey(config.getPrivateKey())
            .setAppId(config.getAppid())
            .setSignType(SignType.RSA2)
            .setIsDev(config.getIsDev());
    }

    public static ConfigStorage createConfigStorage(PlatformWxpayConfig config) {
        ValidUtils.notNull(config);
        return ConfigStorages.createConfigStorage(WxPayConfigStorage.class)
            .setCertFileStr(config.getCertStr())
            .setMchId(config.getMchId())
            .setKey(config.getKeyStr())
            .setSignType(WxSignType.HMAC_SHA256)
            .setIsDev(false)
            .setAppId(config.getAppid());
    }
}
