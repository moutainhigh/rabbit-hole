package com.github.lotus.pay.biz.support.payment.resolve.message;

import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class MessageContext {
    private String accessAppSn;
    private String platform;
    private String feature;

    public Optional<MessageType> asMessageType() {
        for (MessageType type : MessageType.values()) {
            if (type.getPlatform().getCode().equals(platform) && type.feature.getCode().equals(feature)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    @Getter
    @RequiredArgsConstructor
    public enum MessageType {
        WxPayWithPay(PaymentPlatform.WxPay, FeatureType.Pay, "支付通知 - 微信"),
        WxPayWithRefund(PaymentPlatform.WxPay, FeatureType.Refund, "退款通知 - 微信"),
        AliPayWithPay(PaymentPlatform.AliPay, FeatureType.Pay, "支付通知 - 支付宝"),
        AliPayWithRefund(PaymentPlatform.AliPay, FeatureType.Refund, "退款通知 - 支付宝");
        private final PaymentPlatform platform;
        private final FeatureType feature;
        private final String name;
    }

}
