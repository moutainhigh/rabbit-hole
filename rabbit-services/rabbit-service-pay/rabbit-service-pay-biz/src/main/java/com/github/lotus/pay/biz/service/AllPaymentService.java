package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.api.pojo.ro.CreateTradeGoPayRo;
import com.github.lotus.pay.api.pojo.ro.CreateTradeRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.api.pojo.vo.QueryAsyncVo;
import com.github.lotus.pay.api.pojo.vo.RefundStatusSync;
import com.github.lotus.pay.api.pojo.vo.TradeStatusSync;
import com.github.lotus.pay.api.pojo.ro.CloseTradeRo;
import com.github.lotus.pay.biz.pojo.ro.GoRefundRo;
import com.github.lotus.pay.biz.pojo.ro.PayMessageRo;
import com.github.lotus.pay.biz.pojo.ro.RefundMessageRo;
import com.github.lotus.pay.biz.pojo.vo.GoRefundVo;
import com.github.lotus.pay.biz.pojo.vo.WaitPayTradeVo;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface AllPaymentService {
    String createTrade(CreateTradeRo ro);

    GoPayVo createAndGoPay(CreateTradeGoPayRo ro);

    void closeTrade(CloseTradeRo ro);

    WaitPayTradeVo getWaitTrade(String tradeSn);

    GoPayVo goPay(GoPayRo ro);

    GoRefundVo goRefund(GoRefundRo ro);

    QueryAsyncVo<TradeStatusSync> queryTrade(String tradeSn);

    QueryAsyncVo<RefundStatusSync> queryRefund(String refundSn);

    void handlePayMessage(PayMessageRo ro);

    void handleRefundMessage(RefundMessageRo ro);

    void sendAsyncNotifyApp(Long notifyAccessAppId);

    String handleMessage(MessageContext messageContext, String data);
}
