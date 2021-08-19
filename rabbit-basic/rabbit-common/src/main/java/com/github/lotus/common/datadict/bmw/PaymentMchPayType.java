package com.github.lotus.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum PaymentMchPayType implements DataDictEnum {
    Unknown("unknown", "未知"),
    // 支付宝
    AliPay_App("alipay.app", "支付宝 - APP"),
    AliPay_Wap("alipay.native", "支付宝 - Wap/Native"),
    AliPay_PC("alipay.pc", "支付宝 - PC"),
    AliPay_QrCode("alipay.qrcode", "支付宝 - QrCode"),
    //微信
    WxPay_JSAPI("wxpay.jsapi", "微信 - JSAPI"),
    WxPay_App("wxpay.app", "微信 - APP"),
    WxPay_Native("wxpay.native", "微信 - Native");
    private final Serializable code;
    private final String name;
}
