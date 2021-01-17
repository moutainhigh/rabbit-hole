package com.github.lotus.pay.biz.support.payment.resolve;

import cn.hutool.core.util.IdUtil;
import com.github.lotus.pay.biz.support.SNCode;
import com.github.lotus.pay.biz.support.payment.resolve.message.AllInMessageResolve;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageType;
import com.github.lotus.pay.biz.support.payment.resolve.message.rule.payment.AliPayPaymentMessageRule;
import com.github.lotus.pay.biz.support.payment.resolve.message.rule.payment.WxPayPaymentMessageRule;
import com.github.lotus.pay.biz.support.payment.resolve.message.rule.refund.AliPayRefundMessageRule;
import com.github.lotus.pay.biz.support.payment.resolve.message.rule.refund.WxPayRefundMessageRule;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.wxpay.v2.WxPayService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class AllInConfiguration {

    @Bean
    public AllInMessageResolve messageResolve(WxPayService wxPayService,
                                              AliPayService aliPayService) {
        final AllInMessageResolve dataResolve = new AllInMessageResolve();

        // 微信
        dataResolve.addRule(MessageType.WxPayWithRefund, new WxPayRefundMessageRule(wxPayService));
        dataResolve.addRule(MessageType.WxPayWithPayment, new WxPayPaymentMessageRule(wxPayService));

        // 支付宝
        dataResolve.addRule(MessageType.AliPayWithPayment, new AliPayPaymentMessageRule(aliPayService));
        dataResolve.addRule(MessageType.AliPayWithRefund, new AliPayRefundMessageRule(aliPayService));
        return dataResolve;
    }

    @Bean
    public SNCode snCode() {
        return new SNCode(IdUtil.createSnowflake(1, 1));
    }

}
