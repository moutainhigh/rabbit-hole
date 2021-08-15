package com.github.lotus.bmw.biz.docking.wxpay;

import in.hocg.payment.PaymentResponse;
import in.hocg.payment.alipay.v2.response.AliPayHttpResponse;
import in.hocg.payment.wxpay.v2.response.WxPayXmlResponse;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class WxPayHelper {

    public static String toWxPayAmount(BigDecimal v) {
        BigDecimal decimal = BigDecimal.valueOf(100L).multiply(v);
        return decimal.setScale(0, RoundingMode.DOWN).toPlainString();
    }

    public static boolean isSuccess(PaymentResponse response) {
        if (response instanceof AliPayHttpResponse) {
            return "10000".equalsIgnoreCase(((AliPayHttpResponse) response).getCode());
        } else if (response instanceof WxPayXmlResponse) {
            return "SUCCESS".equalsIgnoreCase(((WxPayXmlResponse) response).getResultCode());
        }
        return false;
    }
}
