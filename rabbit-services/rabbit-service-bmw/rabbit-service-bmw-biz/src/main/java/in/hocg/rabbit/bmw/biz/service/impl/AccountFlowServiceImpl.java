package in.hocg.rabbit.bmw.biz.service.impl;

import in.hocg.rabbit.bmw.biz.entity.AccountFlow;
import in.hocg.rabbit.bmw.biz.entity.RefundRecord;
import in.hocg.rabbit.bmw.biz.entity.TradeOrder;
import in.hocg.rabbit.bmw.biz.mapper.AccountFlowMapper;
import in.hocg.rabbit.bmw.biz.service.AccountFlowService;
import in.hocg.rabbit.bmw.biz.service.RefundRecordService;
import in.hocg.rabbit.bmw.biz.service.TradeOrderService;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.common.datadict.bmw.AccountFlowDire;
import in.hocg.rabbit.common.datadict.bmw.AccountFlowType;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * [支付模块] 账户流水表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccountFlowServiceImpl extends AbstractServiceImpl<AccountFlowMapper, AccountFlow> implements AccountFlowService {
    private final RefundRecordService refundRecordService;
    private final TradeOrderService tradeOrderService;

    @Override
    public AccountFlow createInRefundFlow(Long refundRecordId) {
        RefundRecord refundRecord = refundRecordService.getById(refundRecordId);

        AccountFlow entity = new AccountFlow();
        entity.setRefId(refundRecordId);
        entity.setRefType(RefType.RefundRecord.getCodeStr());
        entity.setType(AccountFlowType.Refund.getCodeStr());
        entity.setDire(AccountFlowDire.In.getCodeStr());
        entity.setBookingActId(refundRecord.getRefundActId());
        entity.setBookingAmt(refundRecord.getRefundAmt());
        entity.setBookingAt(refundRecord.getFinishedAt());
        entity.setCreatedAt(LocalDateTime.now());

        this.validInsert(entity);
        return entity;
    }

    @Override
    public AccountFlow createTradeFlow(Long tradeOrderId) {
        TradeOrder tradeOrder = tradeOrderService.getById(tradeOrderId);

        AccountFlow entity = new AccountFlow();
        entity.setRefId(tradeOrderId);
        entity.setRefType(RefType.TradeOrder.getCodeStr());
        entity.setType(AccountFlowType.Trade.getCodeStr());
        entity.setDire(AccountFlowDire.Out.getCodeStr());
        entity.setBookingActId(tradeOrder.getPayActId());
        entity.setBookingAmt(tradeOrder.getTradeAmt());
        entity.setBookingAt(tradeOrder.getFinishedAt());
        entity.setCreatedAt(LocalDateTime.now());

        this.validInsert(entity);
        return entity;
    }
}
