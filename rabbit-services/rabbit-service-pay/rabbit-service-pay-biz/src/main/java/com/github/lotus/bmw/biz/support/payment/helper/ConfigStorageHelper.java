package com.github.lotus.bmw.biz.support.payment.helper;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
        throw new UnsupportedOperationException();
    }

    public static ConfigStorage createAlipayConfigStorage(String config) {
        ValidUtils.notNull(config);
        return ConfigStorages.createConfigStorage(AliPayConfigStorage.class)
            .setAliPayPublicKey(null)
            .setPrivateKey(null)
            .setAppId(null)
            .setSignType(SignType.RSA2)
            .setIsDev(null);
    }

    public static ConfigStorage createWxpayConfigStorage(String config) {
        ValidUtils.notNull(config);
        return ConfigStorages.createConfigStorage(WxPayConfigStorage.class)
            .setCertFileStr(null)
            .setMchId(null)
            .setKey(null)
            .setSignType(WxSignType.HMAC_SHA256)
            .setIsDev(false)
            .setAppId(null);
    }
}
