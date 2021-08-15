package com.github.lotus.bmw.biz.support.payment.helper;

import com.github.lotus.common.datadict.pay.PaymentPlatform;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.payment.ConfigStorage;
import in.hocg.payment.PaymentResponse;
import in.hocg.payment.PaymentService;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.response.AliPayHttpResponse;
import in.hocg.payment.wxpay.v2.WxPayService;
import in.hocg.payment.wxpay.v2.response.WxPayXmlResponse;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class RequestHelper {

    public static PaymentService<?> getPayService(PaymentPlatform paymentPlatform, ConfigStorage configStorage) {
        switch (paymentPlatform) {
            case AliPay:
                return PaymentServices.createPaymentService(AliPayService.class, configStorage);
            case WxPay:
                return PaymentServices.createPaymentService(WxPayService.class, configStorage);
            case Unknown:
            default:
                throw ServiceException.wrap("暂时不支持");
        }
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
