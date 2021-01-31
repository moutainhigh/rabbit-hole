package com.github.lotus.pay.biz.support.payment.resolve.message.rule.pay;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.common.datadict.bmw.TradeStatus;
import com.github.lotus.pay.biz.pojo.ro.PayMessageRo;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.support.payment.helper.PaymentHelper;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
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
public class AliPayPayMessageRule extends StringResolve.StringRule<TradeStatusSyncMessage, TradeStatusSyncMessage.Result> {
    public AliPayPayMessageRule(AliPayService payService) {
        super(new StringConvert<TradeStatusSyncMessage>() {
            @Override
            public <R extends TradeStatusSyncMessage> R convert(String body, Class<R> clazz) {
                return payService.message(URLDecoder.decode(body), clazz);
            }
        }, AliPayPayMessageRule::handleMessage);

    }

    protected static TradeStatusSyncMessage.Result handleMessage(TradeStatusSyncMessage message, Map<String, Object> args) {
        try {
            final StringMap<Object> lambdaMap = (StringMap<Object>) args;
            final Long accessPlatformId = (Long) lambdaMap.get(MessageContext::getAccessPlatformId);
            final String platformType = lambdaMap.getAsString(MessageContext::getPlatform);
            final String feature = lambdaMap.getAsString(MessageContext::getFeature);

            final String outTradeNo = message.getOutTradeNo();
            final String tradeNo = message.getTradeNo();
            final TradeStatus tradeStatus = PaymentHelper.aliPayAsTradeStatus(message.getTradeStatus());
            PaymentPlatform paymentPlatform = PaymentHelper.asPaymentPlatform(platformType);
            final LocalDateTime paymentAt = AliPayPayMessageRule.convertDatetime(message.getGmtPayment());
            final String totalAmount = message.getTotalAmount();
            final String buyerPayAmount = message.getBuyerPayAmount();
            final PayMessageRo ro = new PayMessageRo()
                .setAccessPlatformId(accessPlatformId)
                .setTradeStatus(tradeStatus)
                .setTradeNo(tradeNo)
                .setTradeSn(outTradeNo)
                .setPaymentAt(paymentAt)
                .setTotalFee(new BigDecimal(totalAmount))
                .setBuyerPayFee(new BigDecimal(buyerPayAmount))
                .setPlatformType(paymentPlatform);

            SpringContext.getBean(AllPaymentService.class).handlePayMessage(ro);
            return TradeStatusSyncMessage.Result.builder().result("success").build();
        } catch (Exception e) {
            return TradeStatusSyncMessage.Result.builder().result("fail").build();
        }
    }


    private static LocalDateTime convertDatetime(String datetime) {
        return DateUtil.parseLocalDateTime(datetime, DatePattern.NORM_DATETIME_PATTERN);
    }
}
