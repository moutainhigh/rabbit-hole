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
import in.hocg.boot.utils.lambda.map.StringMap;
import in.hocg.boot.web.SpringContext;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.message.TradeStatusSyncMessage;
import in.hocg.payment.convert.StringConvert;
import in.hocg.payment.resolve.StringResolve;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AliPayPaymentMessageRule extends StringResolve.StringRule<TradeStatusSyncMessage, TradeStatusSyncMessage.Result> {
    public AliPayPaymentMessageRule(AliPayService payService) {
        super(new StringConvert<TradeStatusSyncMessage>() {
            @Override
            public <R extends TradeStatusSyncMessage> R convert(String body, Class<R> clazz) {
                return payService.message(URLDecoder.decode(body), clazz);
            }
        }, AliPayPaymentMessageRule::handleMessage);

    }

    protected static TradeStatusSyncMessage.Result handleMessage(TradeStatusSyncMessage message, Map<String, Object> args) {
        try {
            final StringMap<Object> lambdaMap = (StringMap<Object>) args;
            final String appid = lambdaMap.getAsString(MessageContext::getAppid);
            final Integer channel = lambdaMap.getAsInt(MessageContext::getPlatformTyp);
            final Integer feature = lambdaMap.getAsInt(MessageContext::getFeature);
            final PaymentWayType paymentWay = ICode.of(lambdaMap.getAsInt(MessageContext::getPaymentWay), PaymentWayType.class).orElse(PaymentWayType.Unknown);

            final String outTradeNo = message.getOutTradeNo();
            final String tradeNo = message.getTradeNo();
            final TradeStatus tradeStatus = AliPayPaymentMessageRule.convertStatus(message.getTradeStatus());
            final LocalDateTime paymentAt = AliPayPaymentMessageRule.convertDatetime(message.getGmtPayment());
            final String totalAmount = message.getTotalAmount();
            final String buyerPayAmount = message.getBuyerPayAmount();
            final PaymentMessageRo ro = new PaymentMessageRo()
                .setAppid(LangUtils.getOrDefault(message.getAppId(), appid))
                .setTradeStatus(tradeStatus)
                .setTradeNo(tradeNo)
                .setTradeSn(outTradeNo)
                .setPaymentAt(paymentAt)
                .setTotalFee(new BigDecimal(totalAmount))
                .setBuyerPayFee(new BigDecimal(buyerPayAmount))
                .setPlatformType(channel)
                .setPaymentWay(paymentWay);

            SpringContext.getBean(AllPaymentService.class).handlePaymentMessage(ro);
            return TradeStatusSyncMessage.Result.builder().result("success").build();
        } catch (Exception e) {
            return TradeStatusSyncMessage.Result.builder().result("fail").build();
        }
    }

    private static TradeStatus convertStatus(String tradeStatus) {
        switch (tradeStatus) {
            case "TRADE_SUCCESS":
                return TradeStatus.Success;
            case "TRADE_CLOSED":
                return TradeStatus.Closed;
            default:
                return TradeStatus.Fail;
        }
    }

    private static LocalDateTime convertDatetime(String datetime) {
        return DateUtil.parseLocalDateTime(datetime, DatePattern.NORM_DATETIME_PATTERN);
    }
}
