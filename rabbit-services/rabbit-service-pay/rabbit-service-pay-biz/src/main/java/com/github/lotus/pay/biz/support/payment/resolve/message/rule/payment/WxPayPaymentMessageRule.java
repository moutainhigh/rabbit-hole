package com.github.lotus.pay.biz.support.payment.resolve.message.rule.payment;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.lotus.pay.biz.enumns.PaymentWayType;
import com.github.lotus.pay.biz.enumns.TradeStatus;
import com.github.lotus.pay.biz.pojo.ro.PaymentMessageRo;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.utils.lambda.map.LambdaMap;
import in.hocg.boot.web.SpringContext;
import in.hocg.payment.convert.StringConvert;
import in.hocg.payment.resolve.StringResolve;
import in.hocg.payment.wxpay.v2.WxPayService;
import in.hocg.payment.wxpay.v2.message.UnifiedOrderMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WxPayPaymentMessageRule extends StringResolve.StringRule<UnifiedOrderMessage, UnifiedOrderMessage.Result> {
    public WxPayPaymentMessageRule(WxPayService payService) {
        super(new StringConvert<UnifiedOrderMessage>() {
            @Override
            public <R extends UnifiedOrderMessage> R convert(String body, Class<R> clazz) {
                return payService.message(body, clazz);
            }
        }, WxPayPaymentMessageRule::handleMessage);
    }

    protected static UnifiedOrderMessage.Result handleMessage(UnifiedOrderMessage message, Map<String, Object> args) {
        try {
            final LambdaMap<Object> lambdaMap = (LambdaMap<Object>) args;
            final String appid = lambdaMap.getAsString(MessageContext::getAppid);
            final Integer channel = lambdaMap.getAsInt(MessageContext::getPlatformTyp);
            final Integer feature = lambdaMap.getAsInt(MessageContext::getFeature);
            PaymentWayType paymentWay = ICode.of(lambdaMap.getAsInt(MessageContext::getPaymentWay), PaymentWayType.class).orElse(PaymentWayType.Unknown);

            final String outTradeNo = message.getOutTradeNo();
            final String tradeNo = message.getTransactionId();
            final TradeStatus tradeStatus = WxPayPaymentMessageRule.convertStatus(message.getResultCode());
            final LocalDateTime paymentAt = WxPayPaymentMessageRule.convertDatetime(message.getTimeEnd());
            paymentWay = LangUtils.getOrDefault(WxPayPaymentMessageRule.convertTradeType(message.getTradeType()), paymentWay);
            final BigDecimal totalAmount = WxPayPaymentMessageRule.convertBigDecimal(new BigDecimal(message.getTotalFee()));
            final BigDecimal buyerPayAmount = WxPayPaymentMessageRule.convertBigDecimal(new BigDecimal(message.getSettlementTotalFee()));
            final PaymentMessageRo ro = new PaymentMessageRo()
                .setAppid(LangUtils.getOrDefault(message.getAppId(), appid))
                .setTradeStatus(tradeStatus)
                .setTradeNo(tradeNo)
                .setTradeSn(outTradeNo)
                .setPaymentAt(paymentAt)
                .setTotalFee(totalAmount)
                .setBuyerPayFee(buyerPayAmount)
                .setPlatformType(channel)
                .setPaymentWay(paymentWay);

            SpringContext.getBean(AllPaymentService.class).handlePaymentMessage(ro);
            return UnifiedOrderMessage.Result.builder().returnMsg("SUCCESS").returnCode("OK").build();
        } catch (Exception e) {
            return UnifiedOrderMessage.Result.builder().returnMsg("FAIL").returnCode("FAIL").build();
        }
    }

    private static PaymentWayType convertTradeType(String tradeType) {
        switch (tradeType.toUpperCase()) {
            case "JSAPI":
                return PaymentWayType.WxPayWithJSAPI;
            case "NATIVE":
                return PaymentWayType.WxPayWithNative;
            case "APP":
                return PaymentWayType.WxPayWithApp;
            default:
                return PaymentWayType.Unknown;

        }
    }

    private static BigDecimal convertBigDecimal(BigDecimal v) {
        return v.multiply(BigDecimal.valueOf(100L));
    }

    private static LocalDateTime convertDatetime(String datetime) {
        return DateUtil.parseLocalDateTime(datetime, DatePattern.NORM_DATETIME_PATTERN);
    }

    private static TradeStatus convertStatus(String resultCode) {
        switch (resultCode.toUpperCase()) {
            // 退款成功
            case "SUCCESS": {
                return TradeStatus.Success;
            }
            // 退款关闭
            case "FAIL":
            default:
                return TradeStatus.Fail;
        }
    }
}
