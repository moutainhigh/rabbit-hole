package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.common.datadict.pay.RefundStatus;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.biz.entity.AccessPlatform;
import com.github.lotus.pay.biz.entity.PayRecord;
import com.github.lotus.pay.biz.entity.PaymentPlatform;
import com.github.lotus.pay.biz.entity.RefundRecord;
import com.github.lotus.pay.biz.entity.Trade;
import com.github.lotus.pay.biz.mapper.PaymentPlatformMapper;
import com.github.lotus.pay.biz.mapstruct.PaymentPlatformMapping;
import com.github.lotus.pay.biz.mapstruct.RefundRecordMapping;
import com.github.lotus.pay.biz.pojo.ro.GoRefundRo;
import com.github.lotus.pay.biz.pojo.vo.GoRefundVo;
import com.github.lotus.pay.biz.service.AccessPlatformService;
import com.github.lotus.pay.biz.service.PayRecordService;
import com.github.lotus.pay.biz.service.PaymentModeService;
import com.github.lotus.pay.biz.service.PaymentPlatformService;
import com.github.lotus.pay.biz.service.RefundRecordService;
import com.github.lotus.pay.biz.service.TradeService;
import com.github.lotus.pay.biz.support.SNCodeService;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import com.github.lotus.pay.biz.support.payment.pojo.request.CloseTradeRequest;
import com.github.lotus.pay.biz.support.payment.pojo.request.GoPaymentRequest;
import com.github.lotus.pay.biz.support.payment.pojo.request.GoRefundRequest;
import com.github.lotus.pay.biz.support.payment.pojo.response.GoPaymentResponse;
import com.github.lotus.pay.biz.support.payment.pojo.response.GoRefundResponse;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * [支付网关] 支付平台表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentPlatformServiceImpl extends AbstractServiceImpl<PaymentPlatformMapper, PaymentPlatform>
    implements PaymentPlatformService {
    private final AccessPlatformService accessPlatformService;
    private final TradeService tradeService;
    private final PayRecordService payRecordService;
    private final PaymentPlatformMapping mapping;
    private final SNCodeService codeService;
    private final RefundRecordService refundRecordService;
    private final RefundRecordMapping refundRecordMapping;
    private final PaymentModeService paymentModeService;

    @Override
    public boolean closeTrade(Long accessPlatformId, String tradeSn) {
        ConfigStorageDto configStorage = accessPlatformService.getConfigStorage(accessPlatformId);
        return CloseTradeRequest.builder().configStorage(configStorage).tradeSn(tradeSn).build().request();
    }

    @Override
    public GoPayVo payTrade(GoPayRo ro) {
        LocalDateTime now = LocalDateTime.now();
        String tradeSn = ro.getTradeSn();
        String clientIp = ro.getClientIp();
        String payMode = ro.getPayMode();
        String wxOpenId = ro.getWxOpenId();

        Trade trade = tradeService.getByTradeSn(tradeSn).orElseThrow(() -> ServiceException.wrap("未找到交易账单"));
        Long tradeId = trade.getId();
        Long accessAppId = trade.getAccessAppId();

        AccessPlatform accessPlatform = accessPlatformService.getByAccessAppIdAndPayMode(accessAppId, payMode).orElseThrow(() -> ServiceException.wrap("未授权接入该支付方式"));
        Long accessPlatformId = accessPlatform.getId();
        ConfigStorageDto configStorage = accessPlatformService.getConfigStorage(accessPlatformId);

        // 新增支付记录
        PayRecord payRecord = new PayRecord()
            .setAccessPlatformId(accessPlatformId)
            .setPayMode(payMode)
            .setTradeId(tradeId)
            .setCreatedAt(now)
            .setCreatedIp(clientIp);
        payRecordService.validInsert(payRecord);

        BigDecimal totalFee = trade.getTotalFee();
        final GoPaymentResponse result = GoPaymentRequest.builder()
            .configStorage(configStorage).payMode(payMode).payAmount(totalFee)
            .tradeSn(tradeSn).wxOpenId(wxOpenId).quitUrl(null)
            .build().request();
        return mapping.asGoPayVo(result);
    }

    @Override
    public GoRefundVo refundTrade(GoRefundRo ro) {
        LocalDateTime now = LocalDateTime.now();
        String clientIp = ro.getClientIp();
        final String tradeSn = ro.getTradeSn();

        Trade trade = tradeService.getByTradeSn(tradeSn).orElseThrow(() -> ServiceException.wrap("未找到交易账单"));
        Long accessPlatformId = trade.getAccessPlatformId();

        final AccessPlatform accessPlatform = accessPlatformService.getById(accessPlatformId);
        if (Objects.isNull(accessPlatform)) {
            log.info("交易单:[{}]上的支付平台[id={}]未找到", trade.getTradeSn(), accessPlatformId);
            ValidUtils.fail("交易单支付平台未找到");
        }

        final String refundSn = codeService.getRefundSNCode();
        RefundRecord entity = refundRecordMapping.asRefundRecord(ro)
            .setRefundSn(refundSn)
            .setRefundStatus(RefundStatus.Pending.getCode())
            .setCreatedAt(now)
            .setCreatedIp(clientIp);

        final GoRefundResponse result = GoRefundRequest.builder()
            .tradeSn(trade.getTradeSn())
            .tradeNo(trade.getTradeNo())
            .refundSn(entity.getRefundSn())
            .totalFee(trade.getTotalFee())
            .refundFee(entity.getRefundFee())
            .build().request();
        entity.setRefundTradeNo(result.getRefundTradeNo());
        refundRecordService.validInsert(entity);
        return new GoRefundVo().setRefundSn(entity.getRefundSn());
    }

}
