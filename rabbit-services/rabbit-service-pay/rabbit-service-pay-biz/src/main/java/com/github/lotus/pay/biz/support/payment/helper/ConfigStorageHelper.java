package com.github.lotus.pay.biz.support.payment.helper;

import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.payment.ConfigStorage;
import in.hocg.payment.ConfigStorages;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.sign.SignType;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ConfigStorageHelper {

    public static ConfigStorage createAliPayConfigStorage(PlatformAlipayConfig config) {
        ValidUtils.notNull(config);
        return ConfigStorages.createConfigStorage(AliPayConfigStorage.class)
            .setAliPayPublicKey(config.getPublicKey())
            .setPrivateKey(config.getPrivateKey())
            .setAppId(config.getAppid())
            .setSignType(SignType.RSA2)
            .setIsDev(config.getIsDev());
    }

    public static ConfigStorage createWxPayConfigStorage(PlatformWxpayConfig config) {
        return null;
    }
}
