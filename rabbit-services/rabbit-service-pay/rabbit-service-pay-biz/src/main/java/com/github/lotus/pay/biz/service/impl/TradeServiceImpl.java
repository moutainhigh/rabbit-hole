package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.common.datadict.bmw.RefundStatus;
import com.github.lotus.common.datadict.bmw.TradeStatus;
import com.github.lotus.pay.api.pojo.ro.CreateTradeRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.api.pojo.vo.QueryAsyncVo;
import com.github.lotus.pay.api.pojo.vo.RefundStatusSync;
import com.github.lotus.pay.api.pojo.vo.TradeStatusSync;
import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.entity.AccessPlatform;
import com.github.lotus.pay.biz.entity.PayRecord;
import com.github.lotus.pay.biz.entity.RefundRecord;
import com.github.lotus.pay.biz.entity.Trade;
import com.github.lotus.pay.biz.mapper.TradeMapper;
import com.github.lotus.pay.biz.mapstruct.TradeMapping;
import com.github.lotus.pay.api.pojo.ro.CloseTradeRo;
import com.github.lotus.pay.biz.pojo.ro.GoRefundRo;
import com.github.lotus.pay.biz.pojo.ro.PayMessageRo;
import com.github.lotus.pay.biz.pojo.ro.RefundMessageRo;
import com.github.lotus.pay.biz.pojo.vo.GoRefundVo;
import com.github.lotus.pay.biz.pojo.vo.WaitPayTradeVo;
import com.github.lotus.pay.biz.service.AccessAppService;
import com.github.lotus.pay.biz.service.AccessPlatformService;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.service.NotifyAccessAppService;
import com.github.lotus.pay.biz.service.PayRecordService;
import com.github.lotus.pay.biz.service.PaymentPlatformService;
import com.github.lotus.pay.biz.service.RefundRecordService;
import com.github.lotus.pay.biz.service.TradeService;
import com.github.lotus.pay.biz.support.SNCodeService;
import com.github.lotus.pay.biz.support.payment.PaymentHelper;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.exception.ServiceException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * [支付网关] 交易账单表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TradeServiceImpl extends AbstractServiceImpl<TradeMapper, Trade> implements TradeService {
    private final TradeMapping mapping;
    private final SNCodeService snCodeService;
    private final PayRecordService payRecordService;
    private final AccessAppService accessAppService;
    private final PaymentPlatformService paymentPlatformService;
    private final AccessPlatformService accessPlatformService;
    private final RefundRecordService refundRecordService;
    private final AllPaymentService allPaymentService;
    private final NotifyAccessAppService notifyAccessAppService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createTrade(CreateTradeRo ro) {
        LocalDateTime now = LocalDateTime.now();
        String clientIp = ro.getClientIp();
        String outTradeSn = ro.getOutTradeSn();
        String appid = ro.getAppid();
        String notifyUrl = ro.getNotifyUrl();
        String tradeSn = snCodeService.getTransactionSNCode();

        Optional<Trade> tradeOpt = this.getByOutTradeSn(outTradeSn);
        if (tradeOpt.isPresent()) {
            return tradeOpt.get().getTradeSn();
        }

        AccessApp accessApp = accessAppService.getByEncoding(appid)
            .orElseThrow(() -> ServiceException.wrap("未授权接入方"));
        Long accessAppId = accessApp.getId();

        Trade entity = mapping.asTrade(ro)
            .setAccessAppId(accessAppId)
            .setTradeSn(tradeSn)
            .setTradeStatus(TradeStatus.Init.getCode())
            .setCreatedAt(now)
            .setNotifyUrl(notifyUrl)
            .setCreatedIp(clientIp);
        this.validInsert(entity);
        return tradeSn;
    }

    private Optional<Trade> getByOutTradeSn(String outTradeSn) {
        return lambdaQuery().eq(Trade::getOutTradeSn, outTradeSn).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeTrade(CloseTradeRo ro) {
        LocalDateTime now = LocalDateTime.now();
        String tradeSn = ro.getTradeSn();
        final String clientIp = ro.getClientIp();

        final Trade trade = this.getByTradeSn(tradeSn)
            .orElseThrow(() -> ServiceException.wrap("未找到交易账单"));
        final Long tradeId = trade.getId();

        // 如果是初始化状态可直接关闭
        if (TradeStatus.Init.eq(trade.getTradeStatus())) {
            Trade update = new Trade().setFinishAt(now).setTradeStatus(TradeStatus.Close.getCode()).setUpdatedAt(now).setUpdatedIp(clientIp);
            boolean isOk = this.updateByIdAndTradeStatus(update, tradeId, TradeStatus.Init.getCode());
            ValidUtils.isTrue(isOk, "系统繁忙");
            return;
        }

        Trade update = new Trade().setFinishAt(now).setTradeStatus(TradeStatus.Close.getCode()).setUpdatedAt(now).setUpdatedIp(clientIp);
        boolean isOk = this.updateByIdAndTradeStatus(update, tradeId, TradeStatus.Pending.getCode());
        ValidUtils.isTrue(isOk, "系统繁忙");

        final List<PayRecord> payRecords = payRecordService.listByTradeId(trade.getId());

        for (PayRecord record : payRecords) {
            Long accessPlatformId = record.getAccessPlatformId();
            boolean isClosed = paymentPlatformService.closeTrade(accessPlatformId, tradeSn);
            log.info("交易单:[tradeSn={}]的交易记录:[ID={}], 关闭结果:[{}]", tradeSn, record.getId(), isClosed);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WaitPayTradeVo getPendingTradeByTradeSn(String tradeSn) {
        Trade trade = getByTradeSn(tradeSn)
            .orElseThrow(() -> ServiceException.wrap("未找到交易账单"));
        return new WaitPayTradeVo()
            .setTotalFee(trade.getTotalFee())
            .setExpireAt(trade.getExpireAt())
            .setCreatedAt(trade.getCreatedAt());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<Trade> getByTradeSn(String tradeSn) {
        return lambdaQuery().eq(Trade::getTradeSn, tradeSn).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoPayVo goPay(GoPayRo ro) {
        LocalDateTime now = LocalDateTime.now();
        final String clientIp = ro.getClientIp();
        final String tradeSn = ro.getTradeSn();

        final Trade trade = this.getByTradeSn(tradeSn).orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        final Long tradeId = trade.getId();

        if (TradeStatus.Init.eq(trade.getTradeStatus())) {
            Trade update = new Trade().setTradeStatus(TradeStatus.Pending.getCode()).setUpdatedAt(now).setUpdatedIp(clientIp);
            boolean isOk = updateByIdAndTradeStatus(update, tradeId, TradeStatus.Init.getCode());
            ValidUtils.isTrue(isOk, "系统繁忙");
        } else if (TradeStatus.Pending.eq(trade.getTradeStatus())) {
            log.info("不进行处理");
        } else {
            throw ServiceException.wrap("系统繁忙");
        }

        return paymentPlatformService.payTrade(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoRefundVo goRefund(GoRefundRo ro) {
        final String tradeSn = ro.getTradeSn();
        String outRefundSn = ro.getOutRefundSn();
        String refundReason = ro.getRefundReason();
        BigDecimal refundFee = ro.getRefundFee();

        final Trade trade = this.getByTradeSn(tradeSn).orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        if (!TradeStatus.Success.eq(trade.getTradeStatus())) {
            ValidUtils.fail("交易未完成");
        }
        Long tradeId = trade.getId();
        return paymentPlatformService.refundTrade(ro);
    }

    @Override
    public QueryAsyncVo<TradeStatusSync> queryTrade(String tradeSn) {
        final Trade trade = this.getByTradeSn(tradeSn).orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        final Long accessAppId = trade.getAccessAppId();
        final AccessApp paymentApp = accessAppService.getById(accessAppId);
        ValidUtils.notNull(paymentApp, "未找到接入应用");

        final Long accessPlatformId = trade.getAccessPlatformId();
        log.info("交易单:[{}]上的支付平台[id={}]", trade.getTradeSn(), accessPlatformId);

        String platformType = null;
        if (Objects.nonNull(accessPlatformId)) {
            platformType = LangUtils.callIfNotNull(accessPlatformId, accessPlatformService::getById)
                .map(AccessPlatform::getRefType).orElse(null);
        }

        return new QueryAsyncVo<TradeStatusSync>()
            .setPlatformType(platformType)
            .setData(new TradeStatusSync()
                .setOutTradeSn(trade.getOutTradeSn())
                .setTradeSn(trade.getTradeSn())
                .setTotalFee(trade.getTotalFee())
                .setTradeStatus(trade.getTradeStatus())
                .setCreatedAt(trade.getCreatedAt())
                .setExpireAt(trade.getExpireAt())
                .setPayMode(trade.getPayMode())
                .setPaymentAt(trade.getPaymentAt()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryAsyncVo<RefundStatusSync> queryRefund(String refundSn) {
        final RefundRecord refund = refundRecordService.getByRefundSn(refundSn)
            .orElseThrow(() -> ServiceException.wrap("未找到退款单据"));

        Long tradeId = refund.getTradeId();
        final Trade trade = this.getById(tradeId);
        ValidUtils.notNull(trade, "未找到交易单据");

        final Long paymentPlatformId = trade.getAccessPlatformId();
        final AccessPlatform paymentPlatform = accessPlatformService.getById(paymentPlatformId);
        if (Objects.isNull(paymentPlatform)) {
            log.info("交易单:[{}]上的支付平台[id={}]未找到", trade.getTradeSn(), paymentPlatformId);
            ValidUtils.fail("交易单支付平台未找到");
        }

        String platformType = paymentPlatform.getRefType();
        return new QueryAsyncVo<RefundStatusSync>()
            .setPlatformType(platformType)
            .setData(new RefundStatusSync()
                .setOutTradeSn(trade.getOutTradeSn())
                .setTradeSn(trade.getTradeSn())
                .setTotalFee(trade.getTotalFee())
                .setRefundSn(refundSn)
                .setOutRefundSn(refund.getOutRefundSn())
                .setRefundStatus(refund.getRefundStatus())
                .setRefundFee(refund.getRefundFee())
                .setSettlementRefundFee(refund.getSettlementRefundFee())
                .setRefundAt(refund.getRefundAt()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handlePayMessage(PayMessageRo ro) {
        LocalDateTime now = LocalDateTime.now();
        String clientIp = ro.getClientIp();
        final String accessAppSn = ro.getAccessAppSn();
        String tradeNo = ro.getTradeNo();
        String tradeSn = ro.getTradeSn();
        PaymentPlatform platformType = ro.getPlatformType();
        BigDecimal buyerPayFee = ro.getBuyerPayFee();
        String tradeStatus = PaymentHelper.toTradeStatus(ro.getTradeStatus());
        String payMode = PaymentHelper.toPayMode(ro.getPayMode());

        AccessPlatform accessPlatform = accessPlatformService.getByAppidAndRefType(accessAppSn, platformType.getCode())
            .orElseThrow(() -> ServiceException.wrap("未开通的支付平台"));

        final Trade trade = this.getByTradeSn(tradeSn).orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        ValidUtils.isTrue(trade.getTotalFee().compareTo(ro.getTotalFee()) == 0, "交易金额不相符");

        // 如果已经处理
        if (LangUtils.equals(trade.getTradeNo(), tradeNo)
            && LangUtils.equals(trade.getTradeStatus(), tradeStatus)) {
            return;
        }

        final Trade update = new Trade()
            .setBuyerPayFee(buyerPayFee)
            .setPayMode(payMode)
            .setTradeStatus(tradeStatus)
            .setFinishAt(now)
            .setAccessPlatformId(accessPlatform.getId())
            .setTradeNo(tradeNo)
            .setPaymentAt(ro.getPaymentAt())
            .setUpdatedAt(now)
            .setUpdatedIp(clientIp);

        final Long tradeId = trade.getId();

        boolean isOk = updateByIdAndTradeStatus(update, tradeId, TradeStatus.Pending.getCode());
        ValidUtils.isTrue(isOk, "交易失败");

        // 发送异步通知
        final Long notifyAccessAppId = notifyAccessAppService.createPayNotify(tradeId);
        allPaymentService.sendAsyncNotifyApp(notifyAccessAppId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleRefundMessage(RefundMessageRo ro) {
        LocalDateTime now = LocalDateTime.now();
        final String clientIp = ro.getClientIp();

        final String refundSn = ro.getRefundSn();
        final RefundRecord refundRecord = refundRecordService.getByRefundSn(refundSn).orElseThrow(() -> ServiceException.wrap("未找到退款单据失败"));

        String refundStatus = PaymentHelper.toRefundStatus(ro.getRefundStatus());

        // 如果已经处理
        if (LangUtils.equals(refundRecord.getRefundSn(), refundSn)
            && LangUtils.equals(refundStatus, refundRecord.getRefundStatus())) {
            return;
        }

        String refundTradeNo = ro.getRefundTradeNo();
        BigDecimal settlementRefundFee = ro.getSettlementRefundFee();
        LocalDateTime refundAt = ro.getRefundAt();
        final RefundRecord updated = new RefundRecord()
            .setRefundTradeNo(refundTradeNo)
            .setRefundStatus(refundStatus)
            .setRefundAt(refundAt)
            .setSettlementRefundFee(settlementRefundFee)
            .setUpdateIp(clientIp)
            .setUpdatedAt(now);
        final Long refundRecordId = refundRecord.getId();
        boolean isOk = refundRecordService.updateByIdAndRefundStatus(updated, refundRecordId, RefundStatus.Pending.getCode());
        ValidUtils.isTrue(isOk, "退款失败");

        // 发送异步通知
        final Long notifyAccessAppId = notifyAccessAppService.createRefundNotify(refundRecordId);
        allPaymentService.sendAsyncNotifyApp(notifyAccessAppId);
    }

    private boolean updateByIdAndTradeStatus(@NonNull Trade update, @NonNull Long tradeId, @NonNull String... tradeStatus) {
        return lambdaUpdate().eq(Trade::getId, tradeId).in(Trade::getTradeStatus, Lists.newArrayList(tradeStatus)).update(update);
    }

}
