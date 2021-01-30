package com.github.lotus.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum PaymentMode implements DataDictEnum {
    Unknown("unknown", "未知"),
    // 支付宝
    AliPayWithApp("alipay.app", "支付宝 - APP"),
    AliPayWithWap("alipay.native", "支付宝 - Wap/Native"),
    AliPayWithPC("alipay.pc", "支付宝 - PC"),
    AliPayWithQrCode("alipay.qrcode", "支付宝 - QrCode"),
    //微信
    WxPayWithJSAPI("wxpay.jsapi", "微信 - JSAPI"),
    WxPayWithApp("wxpay.app", "微信 - APP"),
    WxPayWithNative("wxpay.native", "微信 - Native");
    private final String code;
    private final String name;
}
