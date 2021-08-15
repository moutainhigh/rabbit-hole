package com.github.lotus.bmw.biz.docking.wxpay;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.github.lotus.bmw.api.pojo.ro.PayTradeRo;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.biz.cache.BmwCacheService;
import com.github.lotus.bmw.biz.docking.PaymentMchDockingService;
import com.github.lotus.bmw.biz.entity.*;
import com.github.lotus.bmw.biz.pojo.dto.PaymentMchRecordDto;
import com.github.lotus.bmw.biz.service.PayRecordService;
import com.github.lotus.bmw.biz.service.PaymentMchRecordService;
import com.github.lotus.bmw.biz.service.RefundRecordService;
import com.github.lotus.bmw.biz.service.TradeOrderService;
import com.github.lotus.bmw.biz.support.payment.pojo.request.GoPayRequest;
import com.github.lotus.bmw.biz.support.payment.pojo.request.GoRefundRequest;
import com.github.lotus.bmw.biz.support.payment.pojo.response.GoPayResponse;
import com.github.lotus.common.datadict.RefType;
import com.github.lotus.common.datadict.bmw.PaymentMchRecordBizType;
import in.hocg.boot.http.log.autoconfiguration.core.HttpLogBervice;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.payment.PaymentService;
import in.hocg.payment.wxpay.v2.message.UnifiedOrderMessage;
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
public class WxpayMchService implements PaymentMchDockingService {
    private final HttpLogBervice httpLogBervice;
    private final PaymentMchRecordService paymentMchRecordService;
    private final BmwCacheService cacheService;
    private final com.github.lotus.bmw.biz.service.PaymentMchService paymentMchService;
    private final PayRecordService payRecordService;
    private final TradeOrderService tradeOrderService;
    private final RefundRecordService refundRecordService;

    @Override
    public PayTradeVo goPay(PayTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        PaymentMch paymentMch = paymentMchService.getByAccessMchIdAndSceneCodeAndPayType(accessMch.getId(), ro.getSceneCode(), ro.getPayType())
            .orElseThrow(() -> ServiceException.wrap("支付类型[{}]未匹配到接入商户支持的支付商户", ro.getPayType()));
        TradeOrder tradeOrder = tradeOrderService.getByAccessMchIdAndOutOrderNoOrOrderNo(accessMch.getId(), ro.getOutOrderNo(), ro.getOrderNo())
            .orElseThrow(() -> ServiceException.wrap("未找到交易单据"));

        PaymentMchRecordDto createDto = new PaymentMchRecordDto();
        createDto.setRefType(RefType.TradeOrder.getCodeStr());
        createDto.setRefId(tradeOrder.getId());
        createDto.setBizType(PaymentMchRecordBizType.GoPay.getCodeStr());
        createDto.setPaymentMchId(paymentMch.getId());

        String orderNo = tradeOrder.getOrderNo();
        GoPayRequest request = GoPayRequest.builder()
            .quitUrl(tradeOrder.getFrontJumpUrl())
            .payAmount(tradeOrder.getTradeAmt())
            .tradeSn(orderNo).build();
        String urlString = request.getClass().getSimpleName();

        String result = httpLogBervice.call(() -> {
            GoPayResponse response = request.request(ro.getPayType());
            return JSON.toJSONString(response);
        }, () -> {
            Serializable logId = httpLogBervice.syncReady(null, null, null, null, null, null, null, null, urlString, null, null);
            createDto.setLogId(Convert.toLong(logId));
            createDto.setAttach(orderNo);
            paymentMchRecordService.create(createDto);
            return logId;
        });
        return new PayTradeVo();
    }

    @Override
    public String goRefund(Long refundRecordId) {
        RefundRecord refundRecord = refundRecordService.getById(refundRecordId);
        TradeOrder tradeOrder = tradeOrderService.getById(refundRecord.getTradeOrderId());
        Long paymentMchId = tradeOrder.getPaymentMchId();

        String orderNo = refundRecord.getOrderNo();
        GoRefundRequest request = GoRefundRequest.builder()
            .refundSn(orderNo)
            .refundFee(refundRecord.getRefundAmt())
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
        return "ok";
    }

    @Override
    public PayRecord getTradeWithPayRecord(String paymentMchCode, Long payRecordId, String ro) {
        PaymentMch paymentMch = paymentMchService.getByEncoding(paymentMchCode)
            .orElseThrow(UnsupportedOperationException::new);
        PaymentService<?> payService = this.getPayService(paymentMch);
        UnifiedOrderMessage message = payService.message(ro, UnifiedOrderMessage.class);
        String orderNo = message.getOutTradeNo();
        String tradeStatus = message.getResultCode();
        BigDecimal messageAmount = WxPayHelper.asAmt(message.getTotalFee());
        TradeOrder tradeOrder = tradeOrderService.getByOrderNo(orderNo).orElseThrow(() -> ServiceException.wrap("交易单不存在"));
        BigDecimal tradeAmt = tradeOrder.getTradeAmt();
        Assert.isTrue(WxPayHelper.isPayed(tradeStatus), "支付商户的支付单[{}]的状态为[{}]，不是已支付", orderNo, tradeStatus);
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
