package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.api.pojo.ro.CloseTradeRo;
import com.github.lotus.pay.api.pojo.ro.CreateTradeGoPayRo;
import com.github.lotus.pay.api.pojo.ro.CreateTradeRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.api.pojo.vo.QueryAsyncVo;
import com.github.lotus.pay.api.pojo.vo.RefundStatusSync;
import com.github.lotus.pay.api.pojo.vo.TradeStatusSync;
import com.github.lotus.pay.biz.pojo.ro.GoRefundRo;
import com.github.lotus.pay.biz.pojo.ro.PayMessageRo;
import com.github.lotus.pay.biz.pojo.ro.RefundMessageRo;
import com.github.lotus.pay.biz.pojo.vo.GoRefundVo;
import com.github.lotus.pay.biz.pojo.vo.WaitPayTradeVo;
import com.github.lotus.pay.biz.service.AccessAppService;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.service.TradeService;
import com.github.lotus.pay.biz.support.payment.helper.RequestHelper;
import com.github.lotus.pay.biz.support.payment.resolve.message.AllInMessageResolve;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
import in.hocg.boot.utils.lambda.map.LambdaMap;
import in.hocg.boot.utils.lambda.map.StringMap;
import in.hocg.payment.PaymentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AllPaymentServiceImpl implements AllPaymentService {
    private final TradeService tradeService;
    private final AccessAppService accessAppService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createTrade(CreateTradeRo ro) {
        return tradeService.createTrade(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoPayVo createAndGoPay(CreateTradeGoPayRo ro) {
        String clientIp = ro.getClientIp();
        String paymentMode = ro.getPaymentMode();

        String tradeSn = this.createTrade(ro);
        GoPayRo goPayRo = new GoPayRo();
        goPayRo.setTradeSn(tradeSn);
        goPayRo.setPayMode(paymentMode);
        goPayRo.setClientIp(clientIp);
        return this.goPay(goPayRo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeTrade(CloseTradeRo ro) {
        tradeService.closeTrade(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WaitPayTradeVo getWaitTrade(String tradeSn) {
        return tradeService.getPendingTradeByTradeSn(tradeSn);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoPayVo goPay(GoPayRo ro) {
        return tradeService.goPay(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoRefundVo goRefund(GoRefundRo ro) {
        return tradeService.goRefund(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryAsyncVo<TradeStatusSync> queryTrade(String tradeSn) {
        return tradeService.queryTrade(tradeSn);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryAsyncVo<RefundStatusSync> queryRefund(String refundSn) {
        return tradeService.queryRefund(refundSn);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handlePayMessage(PayMessageRo ro) {
        tradeService.handlePayMessage(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleRefundMessage(RefundMessageRo ro) {
        tradeService.handleRefundMessage(ro);
    }

    @Async
    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void sendAsyncNotifyApp(Long notifyAccessAppId) {
        accessAppService.sendAsyncNotifyApp(notifyAccessAppId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String handleMessage(MessageContext context, String data) {
        final LambdaMap<Object> args = new StringMap<>();
        args.put(MessageContext::getAccessAppSn, context.getAccessAppSn());
        args.put(MessageContext::getPlatform, context.getPlatform());
        args.put(MessageContext::getFeature, context.getFeature());
        MessageContext.MessageType messageType = context.asMessageType().orElseThrow(UnsupportedOperationException::new);
        AllInMessageResolve messageResolve = RequestHelper.messageResolve(messageType.getPlatform(), context.getAccessAppSn());
        return ((PaymentMessage.Result) messageResolve.handle(messageType, data, args)).string();
    }
}
