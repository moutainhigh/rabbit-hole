package com.github.lotus.pay.biz.support.payment.resolve.message.rule.pay;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.lotus.common.datadict.pay.PayMode;
import com.github.lotus.common.datadict.pay.PaymentPlatform;
import com.github.lotus.common.datadict.pay.TradeStatus;
import com.github.lotus.pay.biz.pojo.ro.PayMessageRo;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.support.payment.helper.PaymentHelper;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
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
public class WxPayPayMessageRule extends StringResolve.StringRule<UnifiedOrderMessage, UnifiedOrderMessage.Result> {
    public WxPayPayMessageRule(WxPayService payService) {
        super(new StringConvert<UnifiedOrderMessage>() {
            @Override
            public <R extends UnifiedOrderMessage> R convert(String body, Class<R> clazz) {
                return payService.message(body, clazz);
            }
        }, WxPayPayMessageRule::handleMessage);
    }

    protected static UnifiedOrderMessage.Result handleMessage(UnifiedOrderMessage message, Map<String, Object> args) {
        try {
            final LambdaMap<Object> lambdaMap = (LambdaMap<Object>) args;
            final Long accessPlatformId = (Long) lambdaMap.get(MessageContext::getAccessPlatformId);
            final String platformType = lambdaMap.getAsString(MessageContext::getPlatform);
            final String feature = lambdaMap.getAsString(MessageContext::getFeature);

            final String outTradeNo = message.getOutTradeNo();
            final String tradeNo = message.getTransactionId();
            TradeStatus tradeStatus = PaymentHelper.wxPayAsTradeStatus(message.getResultCode());
            PayMode payMode = PaymentHelper.wxPayAsPayMode(message.getTradeType());
            PaymentPlatform paymentPlatform = PaymentHelper.asPaymentPlatform(platformType);
            final LocalDateTime paymentAt = WxPayPayMessageRule.convertDatetime(message.getTimeEnd());
            final BigDecimal totalAmount = WxPayPayMessageRule.convertBigDecimal(new BigDecimal(message.getTotalFee()));
            final BigDecimal buyerPayAmount = WxPayPayMessageRule.convertBigDecimal(new BigDecimal(message.getSettlementTotalFee()));
            final PayMessageRo ro = new PayMessageRo()
                .setAccessPlatformId(accessPlatformId)
                .setTradeStatus(tradeStatus)
                .setTradeNo(tradeNo)
                .setTradeSn(outTradeNo)
                .setPaymentAt(paymentAt)
                .setTotalFee(totalAmount)
                .setBuyerPayFee(buyerPayAmount)
                .setPayMode(payMode)
                .setPlatformType(paymentPlatform);

            SpringContext.getBean(AllPaymentService.class).handlePayMessage(ro);
            return UnifiedOrderMessage.Result.builder().returnMsg("SUCCESS").returnCode("OK").build();
        } catch (Exception e) {
            return UnifiedOrderMessage.Result.builder().returnMsg("FAIL").returnCode("FAIL").build();
        }
    }

    private static BigDecimal convertBigDecimal(BigDecimal v) {
        return v.multiply(BigDecimal.valueOf(100L));
    }

    private static LocalDateTime convertDatetime(String datetime) {
        return DateUtil.parseLocalDateTime(datetime, DatePattern.NORM_DATETIME_PATTERN);
    }

}
