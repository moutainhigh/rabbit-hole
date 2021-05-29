package com.github.lotus.pay.biz.support.payment.resolve.message.rule.refund;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.lotus.common.datadict.pay.PaymentPlatform;
import com.github.lotus.common.datadict.pay.RefundStatus;
import com.github.lotus.pay.biz.pojo.ro.RefundMessageRo;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.support.payment.helper.PaymentHelper;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
import in.hocg.boot.utils.lambda.map.LambdaMap;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.payment.convert.StringConvert;
import in.hocg.payment.resolve.StringResolve;
import in.hocg.payment.wxpay.v2.WxPayService;
import in.hocg.payment.wxpay.v2.message.PayRefundMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WxPayRefundMessageRule extends StringResolve.StringRule<PayRefundMessage, PayRefundMessage.Result> {
    public WxPayRefundMessageRule(WxPayService payService) {
        super(new StringConvert<PayRefundMessage>() {
            @Override
            public <R extends PayRefundMessage> R convert(String body, Class<R> clazz) {
                return payService.message(body, clazz);
            }
        }, WxPayRefundMessageRule::handleMessage);
    }

    protected static PayRefundMessage.Result handleMessage(PayRefundMessage message, Map<String, Object> args) {
        try {
            final LambdaMap<Object> lambdaMap = (LambdaMap<Object>) args;
            final Long accessPlatformId = (Long) lambdaMap.get(MessageContext::getAccessPlatformId);
            final String platformType = lambdaMap.getAsString(MessageContext::getPlatform);
            final String feature = lambdaMap.getAsString(MessageContext::getFeature);

            final PayRefundMessage.ReqInfo reqInfo = message.getReqInfo();
            final String refundTradeNo = reqInfo.getRefundId();
            final String refundSn = reqInfo.getOutTradeNo();
            PaymentPlatform paymentPlatform = PaymentHelper.asPaymentPlatform(platformType);
            RefundStatus refundStatus = PaymentHelper.wxpayAsRefundStatus(reqInfo.getRefundStatus());
            final BigDecimal refundFee = WxPayRefundMessageRule.convertBigDecimal(new BigDecimal(reqInfo.getRefundFee()));
            final BigDecimal settlementRefundFee = WxPayRefundMessageRule.convertBigDecimal(new BigDecimal(reqInfo.getSettlementRefundFee()));
            final LocalDateTime refundAt = WxPayRefundMessageRule.convertDatetime(reqInfo.getSuccessTime());
            final RefundMessageRo ro = new RefundMessageRo()
                .setRefundAt(refundAt)
                .setRefundFee(refundFee)
                .setRefundStatus(refundStatus)
                .setSettlementRefundFee(settlementRefundFee)
                .setRefundSn(refundSn)
                .setRefundTradeNo(refundTradeNo)
                .setAccessPlatformId(accessPlatformId)
                .setPlatformType(paymentPlatform);

            SpringContext.getBean(AllPaymentService.class).handleRefundMessage(ro);
            return PayRefundMessage.Result.builder().returnMsg("SUCCESS").returnCode("OK").build();
        } catch (Exception e) {
            return PayRefundMessage.Result.builder().returnMsg("FAIL").returnCode("FAIL").build();
        }
    }

    private static BigDecimal convertBigDecimal(BigDecimal v) {
        return v.multiply(new BigDecimal(100L));
    }

    private static LocalDateTime convertDatetime(String datetime) {
        return DateUtil.parseLocalDateTime(datetime, DatePattern.NORM_DATETIME_PATTERN);
    }
}
