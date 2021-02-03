package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.common.datadict.pay.NotifyStatus;
import com.github.lotus.common.datadict.pay.NotifyType;
import com.github.lotus.pay.biz.entity.NotifyAccessApp;
import com.github.lotus.pay.biz.entity.RefundRecord;
import com.github.lotus.pay.biz.entity.Trade;
import com.github.lotus.pay.biz.mapper.NotifyAccessAppMapper;
import com.github.lotus.pay.biz.service.NotifyAccessAppService;
import com.github.lotus.pay.biz.service.RefundRecordService;
import com.github.lotus.pay.biz.service.TradeService;
import com.github.lotus.pay.biz.support.payment.helper.PaymentHelper;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * [支付网关] 事件通知列表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NotifyAccessAppServiceImpl extends AbstractServiceImpl<NotifyAccessAppMapper, NotifyAccessApp>
    implements NotifyAccessAppService {
    private final TradeService tradeService;
    private final RefundRecordService refundRecordService;

    @Override
    public Long createPayNotify(Long tradeId) {
        final Trade trade = tradeService.getById(tradeId);
        ValidUtils.notNull(trade, "交易单据不存在");
        NotifyStatus notifyStatus = PaymentHelper.tradeStatusToNotifyStatus(trade.getTradeStatus());
        final String tradeSn = trade.getTradeSn();

        final NotifyAccessApp entity = new NotifyAccessApp()
            .setNotifyType(NotifyType.Pay.getCode())
            .setNotifyStatus(notifyStatus.getCode())
            .setTradeSn(tradeSn)
            .setRequestSn(tradeSn)
            .setCreatedAt(LocalDateTime.now());
        this.validInsert(entity);
        return entity.getId();
    }

    @Override
    public Long createRefundNotify(Long refundRecordId) {
        final RefundRecord refundRecord = refundRecordService.getById(refundRecordId);
        ValidUtils.notNull(refundRecord, "退款单据不存在");
        NotifyStatus notifyStatus = PaymentHelper.refundStatusToNotifyStatus(refundRecord.getRefundStatus());

        final Long tradeId = refundRecord.getTradeId();
        Trade trade = tradeService.getById(tradeId);
        final NotifyAccessApp entity = new NotifyAccessApp()
            .setNotifyType(NotifyType.Refund.getCode())
            .setNotifyStatus(notifyStatus.getCode())
            .setTradeSn(trade.getTradeSn())
            .setRequestSn(refundRecord.getRefundSn())
            .setCreatedAt(LocalDateTime.now());
        this.validInsert(entity);
        return entity.getId();
    }
}
