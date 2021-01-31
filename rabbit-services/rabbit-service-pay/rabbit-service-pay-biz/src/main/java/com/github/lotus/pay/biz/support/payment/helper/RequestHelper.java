package com.github.lotus.pay.biz.support.payment.helper;

import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.pay.biz.entity.AccessPlatform;
import com.github.lotus.pay.biz.service.AccessPlatformService;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import com.github.lotus.pay.biz.support.payment.resolve.message.AllInMessageResolve;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
import com.github.lotus.pay.biz.support.payment.resolve.message.rule.pay.AliPayPayMessageRule;
import com.github.lotus.pay.biz.support.payment.resolve.message.rule.pay.WxPayPayMessageRule;
import com.github.lotus.pay.biz.support.payment.resolve.message.rule.refund.AliPayRefundMessageRule;
import com.github.lotus.pay.biz.support.payment.resolve.message.rule.refund.WxPayRefundMessageRule;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.SpringContext;
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

    public AllInMessageResolve messageResolve(Long accessPlatformId) {
        AccessPlatformService accessPlatformService = SpringContext.getApplicationContext().getBean(AccessPlatformService.class);
        AccessPlatform accessPlatform = accessPlatformService.getById(accessPlatformId);
        ValidUtils.notNull(accessPlatform, "错误的支付回调信息");
        ConfigStorageDto configStorage = accessPlatformService.getConfigStorage(accessPlatform.getId());

        final AllInMessageResolve dataResolve = new AllInMessageResolve();
        switch (ICode.ofThrow(accessPlatform.getRefType(), PaymentPlatform.class)) {
            case AliPay:{
                AliPayService payService = (AliPayService) configStorage.getPayService();
                dataResolve.addRule(MessageContext.MessageType.WxPayWithPay, new AliPayPayMessageRule(payService));
                dataResolve.addRule(MessageContext.MessageType.AliPayWithRefund, new AliPayRefundMessageRule(payService));
                break;
            }
            case WxPay:{
                WxPayService payService = (WxPayService) configStorage.getPayService();
                dataResolve.addRule(MessageContext.MessageType.WxPayWithRefund, new WxPayRefundMessageRule(payService));
                dataResolve.addRule(MessageContext.MessageType.WxPayWithPay, new WxPayPayMessageRule(payService));
                break;
            }
            case Unknown:
            default:
                throw new UnsupportedOperationException();
        }
        return dataResolve;
    }


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
