package com.github.lotus.pay.biz.support.payment.resolve.message;

import com.github.lotus.pay.biz.enumns.PaymentPlatformType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum MessageType {
    WxPayWithPayment(PaymentPlatformType.WxPay.getCode(), FeatureType.Payment.getCode(), "支付通知 - 微信"),
    WxPayWithRefund(PaymentPlatformType.WxPay.getCode(), FeatureType.Refund.getCode(), "退款通知 - 微信"),
    AliPayWithPayment(PaymentPlatformType.AliPay.getCode(), FeatureType.Payment.getCode(), "支付通知 - 支付宝"),
    AliPayWithRefund(PaymentPlatformType.AliPay.getCode(), FeatureType.Refund.getCode(), "退款通知 - 支付宝"),
    ;

    private final Integer channel;
    private final Integer feature;
    private final String name;

}
