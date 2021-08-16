package com.github.lotus.bmw.biz.docking.alipay;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.github.lotus.bmw.biz.pojo.ro.GoPayRo;
import com.github.lotus.bmw.biz.pojo.vo.GoPayVo;
import com.github.lotus.bmw.biz.cache.BmwCacheService;
import com.github.lotus.bmw.biz.docking.PaymentMchDockingService;
import com.github.lotus.bmw.biz.entity.*;
import com.github.lotus.bmw.biz.pojo.dto.PaymentMchRecordDto;
import com.github.lotus.bmw.biz.service.PayRecordService;
import com.github.lotus.bmw.biz.service.PaymentMchRecordService;
import com.github.lotus.bmw.biz.service.RefundRecordService;
import com.github.lotus.bmw.biz.service.TradeOrderService;
import com.github.lotus.bmw.biz.support.payment.ConfigStorageDto;
import com.github.lotus.bmw.biz.support.payment.pojo.request.CloseTradeRequest;
import com.github.lotus.bmw.biz.support.payment.pojo.request.GoPayRequest;
import com.github.lotus.bmw.biz.support.payment.pojo.request.GoRefundRequest;
import com.github.lotus.common.datadict.RefType;
import com.github.lotus.common.datadict.bmw.PaymentMchRecordBizType;
import in.hocg.boot.http.log.autoconfiguration.core.HttpLogBervice;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.payment.PaymentService;
import in.hocg.payment.alipay.v2.message.TradeStatusSyncMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AlipayMchService implements PaymentMchDockingService {
    private final HttpLogBervice httpLogBervice;
    private final PaymentMchRecordService paymentMchRecordService;
    private final com.github.lotus.bmw.biz.service.PaymentMchService paymentMchService;
    private final TradeOrderService tradeOrderService;
    private final RefundRecordService refundRecordService;
    private final PayRecordService payRecordService;

    @Override
    public GoPayVo goPay(GoPayRo ro) {
        PaymentMch paymentMch = paymentMchService.getById(ro.getPaymentMchId());
        Assert.notNull(paymentMch, "支付商户未找到");
        TradeOrder tradeOrder = tradeOrderService.getById(ro.getTradeOrderId());
        Assert.notNull(tradeOrder, "未找到交易单据");

        PaymentMchRecordDto createDto = new PaymentMchRecordDto();
        createDto.setRefType(RefType.TradeOrder.getCodeStr());
        createDto.setRefId(tradeOrder.getId());
        createDto.setBizType(PaymentMchRecordBizType.GoPay.getCodeStr());
        createDto.setPaymentMchId(paymentMch.getId());

        String orderNo = tradeOrder.getTradeNo();
        GoPayRequest request = GoPayRequest.builder()
            .payRecordId(ro.getPayRecordId())
            .quitUrl(tradeOrder.getFrontJumpUrl())
            .payAmount(tradeOrder.getTradeAmt())
            .configStorage(new ConfigStorageDto(paymentMch))
            .tradeSn(orderNo).build();
        return request.request(ro.getPayType(), createDto);
    }

    @Override
    public void closeTrade(Long tradeOrderId) {
        TradeOrder tradeOrder = tradeOrderService.getById(tradeOrderId);
        Long paymentMchId = tradeOrder.getPaymentMchId();
        PaymentMch paymentMch = paymentMchService.getById(paymentMchId);

        String orderNo = tradeOrder.getTradeNo();
        CloseTradeRequest request = CloseTradeRequest.builder()
            .configStorage(new ConfigStorageDto(paymentMch))
            .tradeSn(orderNo)
            .build();

        PaymentMchRecordDto createDto = new PaymentMchRecordDto();
        createDto.setRefType(RefType.TradeOrder.getCodeStr());
        createDto.setRefId(tradeOrderId);
        createDto.setBizType(PaymentMchRecordBizType.CloseTrade.getCodeStr());
        createDto.setPaymentMchId(paymentMchId);
        String urlString = request.getClass().getSimpleName();
        String result = httpLogBervice.call(() -> JSON.toJSONString(request.request()), () -> {
            Serializable logId = httpLogBervice.syncReady(null, null, null, null, null, null, null, null, urlString, null, null);
            createDto.setLogId(Convert.toLong(logId));
            createDto.setAttach(orderNo);
            paymentMchRecordService.create(createDto);
            return logId;
        });
        tradeOrderService.closeTrade(tradeOrderId);
    }

    @Override
    public void goRefund(Long refundRecordId) {
        RefundRecord refundRecord = refundRecordService.getById(refundRecordId);
        TradeOrder tradeOrder = tradeOrderService.getById(refundRecord.getTradeOrderId());
        Long paymentMchId = tradeOrder.getPaymentMchId();
        PaymentMch paymentMch = paymentMchService.getById(paymentMchId);

        String orderNo = refundRecord.getRefundNo();
        GoRefundRequest request = GoRefundRequest.builder()
            .refundSn(orderNo)
            .refundFee(refundRecord.getRefundAmt())
            .configStorage(new ConfigStorageDto(paymentMch))
            .build();

        PaymentMchRecordDto createDto = new PaymentMchRecordDto();
        createDto.setRefType(RefType.RefundRecord.getCodeStr());
        createDto.setRefId(refundRecordId);
        createDto.setBizType(PaymentMchRecordBizType.GoRefund.getCodeStr());
        createDto.setPaymentMchId(paymentMchId);
        String urlString = request.getClass().getSimpleName();
        String result = httpLogBervice.call(() -> JSON.toJSONString(request.request()), () -> {
            Serializable logId = httpLogBervice.syncReady(null, null, null, null, null, null, null, null, urlString, null, null);
            createDto.setLogId(Convert.toLong(logId));
            createDto.setAttach(orderNo);
            paymentMchRecordService.create(createDto);
            return logId;
        });
    }

    @Override
    public PayRecord getTradeWithPayRecord(String paymentMchCode, Long payRecordId, String ro) {
        PaymentMch paymentMch = paymentMchService.getByEncoding(paymentMchCode)
            .orElseThrow(UnsupportedOperationException::new);
        PaymentService<?> payService = this.getPayService(paymentMch);
        TradeStatusSyncMessage message = payService.message(ro, TradeStatusSyncMessage.class);
        String orderNo = message.getOutTradeNo();
        String tradeStatus = message.getTradeStatus();
        BigDecimal messageAmount = AliPayHelper.asAmt(message.getTotalAmount());
        TradeOrder tradeOrder = tradeOrderService.getByOrderNo(orderNo).orElseThrow(() -> ServiceException.wrap("交易单不存在"));
        BigDecimal tradeAmt = tradeOrder.getTradeAmt();
        Assert.isTrue(AliPayHelper.isPayed(tradeStatus), "支付商户的支付单[{}]的状态为[{}]，不是已支付", orderNo, tradeStatus);
        Assert.isTrue(messageAmount.equals(tradeAmt), "支付单[{}]交易金额[{}!={}]不符", orderNo, messageAmount, tradeAmt);
        return payRecordService.getById(payRecordId);
    }

    @Override
    @SneakyThrows
    public void notifySuccess() {
        HttpServletResponse response = SpringServletContext.getResponse().orElseThrow(UnsupportedOperationException::new);
        response.getWriter().write("notify_success");
    }

}
