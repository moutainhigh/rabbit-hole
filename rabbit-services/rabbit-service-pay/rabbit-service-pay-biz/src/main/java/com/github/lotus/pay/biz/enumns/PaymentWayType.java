package com.github.lotus.pay.biz.enumns;

import com.github.lotus.pay.biz.support.payment.resolve.message.FeatureType;
import in.hocg.boot.mybatis.plus.autoconfiguration.constant.DataDictEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2020/5/31.
 * email: hocgin@gmail.com
 *
 * @uthor hocgin
 */
@Getter
@ApiModel("支付方式")
@RequiredArgsConstructor
public enum PaymentWayType implements DataDictEnum {
    Unknown(PaymentPlatformType.Unknown, 0, "未知"),
    // 支付宝
    AliPayWithApp(PaymentPlatformType.AliPay, 1, "支付宝 - APP"),
    AliPayWithWap(PaymentPlatformType.AliPay, 2, "支付宝 - Wap/Native"),
    AliPayWithPC(PaymentPlatformType.AliPay, 3, "支付宝 - PC"),
    AliPayWithQrCode(PaymentPlatformType.AliPay, 4, "支付宝 - QrCode"),
    //微信
    WxPayWithJSAPI(PaymentPlatformType.WxPay, 5, "微信 - JSAPI"),
    WxPayWithApp(PaymentPlatformType.WxPay, 6, "微信 - APP"),
    WxPayWithNative(PaymentPlatformType.WxPay, 7, "微信 - Native");
    private final PaymentPlatformType platform;
    private final Integer code;
    private final String name;
    public static final String KEY = "PaymentWay";

    public String getNotifyUrl(FeatureType featureType, String appid) {
        final Integer paymentType = platform.getCode();
        final Integer feature = featureType.getCode();
        final Integer paymentWay = this.getCode();
        return feature + "/" + paymentType + "/" + appid + "/" + paymentWay;
    }
}
