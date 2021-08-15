package com.github.lotus.bmw.biz.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.github.lotus.bmw.api.pojo.ro.GoRefundRo;
import com.github.lotus.bmw.api.pojo.ro.PayTradeRo;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.api.pojo.vo.RefundSyncVo;
import com.github.lotus.bmw.biz.cache.BmwCacheService;
import com.github.lotus.bmw.biz.entity.*;
import com.github.lotus.bmw.biz.mapstruct.RefundRecordMapping;
import com.github.lotus.bmw.biz.pojo.dto.CreateAccountDto;
import com.github.lotus.bmw.biz.service.*;
import com.github.lotus.bmw.biz.docking.alipay.AlipayMchService;
import com.github.lotus.bmw.biz.docking.wxpay.WxpayMchService;
import com.github.lotus.common.datadict.bmw.PaymentMchType;
import com.github.lotus.common.datadict.bmw.RefundStatus;
import com.github.lotus.common.datadict.bmw.TradeOrderStatus;
import com.github.lotus.common.utils.Rules;
import com.github.lotus.bmw.biz.service.SNCodeService;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentMchDockingServiceImpl implements PaymentMchDockingService {
    private final BmwCacheService cacheService;
    private final TradeOrderService tradeOrderService;
    private final RefundRecordService refundRecordService;
    private final SNCodeService codeService;
    private final RefundRecordMapping refundRecordMapping;
    private final PaymentMchService paymentMchService;
    private final AccountService accountService;
    private final AccountFlowService accountFlowService;
    private final SyncAccessMchTaskService syncAccessMchTaskService;
    private final AlipayMchService alipayMchService;
    private final WxpayMchService wxpayMchService;

    @Override
    public PayTradeVo goPay(PayTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        PaymentMch paymentMch = paymentMchService.getByAccessMchIdAndPayType(accessMch.getId(), ro.getPayType())
            .orElseThrow(() -> ServiceException.wrap("支付类型[{}]未匹配到接入商户支持的支付商户", ro.getPayType()));
        Optional<PayTradeVo> opt = Rules.create()
            // 支付宝
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> alipayMchService.goPay(ro)))
            // 微信
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> wxpayMchService.goPay(ro)))
            .of(ICode.ofThrow(paymentMch.getType(), PaymentMchType.class));
        return opt.orElseThrow(UnsupportedOperationException::new);
    }

    @Override
    public RefundSyncVo goRefund(GoRefundRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        Long accessMchId = accessMch.getId();
        LocalDateTime now = LocalDateTime.now();

        TradeOrder tradeOrder = tradeOrderService.getByAccessMchIdAndOutOrderNoOrOrderNo(accessMchId, ro.getOutTradeOrderNo(), ro.getTradeOrderNo())
            .orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        Assert.isTrue(TradeOrderStatus.Payed.eq(tradeOrder.getStatus()), "交易单状态非已支付状态");
        BigDecimal oldRefundAmt = tradeOrder.getRefundAmt();
        BigDecimal tradeAmt = tradeOrder.getTradeAmt();
        BigDecimal newTotalRefundAmt = ro.getRefundAmt().add(tradeOrder.getRefundAmt());
        Assert.isTrue(newTotalRefundAmt.compareTo(tradeAmt) <= 0, "退款金额不符合要求");
        Long tradeOrderId = tradeOrder.getId();

        PaymentMch paymentMch = paymentMchService.getById(tradeOrder.getPaymentMchId());
        Optional<RefundRecord> entityOpt = refundRecordService.getByAccessMchIdAndOutOrderNoOrOrderNo(accessMchId, ro.getOutOrderNo(), null);
        Assert.isTrue(entityOpt.isPresent(), "退款单据已存在");
        Long payActId = tradeOrder.getPayActId();

        // 1. 创建退款单据
        String refundNo = codeService.getRefundNo();
        RefundRecord entity = refundRecordMapping.asRefundRecord(ro);
        entity.setRefundActId(payActId);
        entity.setStatus(RefundStatus.Processing.getCodeStr());
        entity.setTradeOrderId(tradeOrderId);
        entity.setOrderNo(refundNo);
        entity.setAccessMchId(accessMchId);
        entity.setCreatedAt(now);
        Assert.isTrue(refundRecordService.validInsert(entity));
        Long refundRecordId = entity.getId();

        // 2. [支付商户]去退款
        Rules.create()
            // 支付宝
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> alipayMchService.goRefund(refundRecordId)))
            // 微信
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> wxpayMchService.goRefund(refundRecordId)))
            .of(ICode.ofThrow(paymentMch.getType(), PaymentMchType.class));

        // 3. 变更退款状态
        RefundRecord update = new RefundRecord().setStatus(RefundStatus.Success.getCodeStr()).setFinishedAt(now).setLastUpdatedAt(now);
        refundRecordService.updateByIdAndStatus(update, refundRecordId, RefundStatus.Processing.getCodeStr());

        // 4. 修改支付单退款金额
        tradeOrderService.updateRefundAmtById(tradeOrder.getId(), newTotalRefundAmt, oldRefundAmt);

        // 5. 新增账户流水
        accountFlowService.createInRefundFlow(refundRecordId);

        // 6. 创建通知任务 & 通知接入商户
        if (StrUtil.isNotBlank(entity.getNotifyUrl())) {
            syncAccessMchTaskService.createRefund(refundRecordId);
        }
        return refundRecordService.convertRefundSyncVo(entity);
    }

    @Override
    public Optional<Account> getAccount(Long userId, Long accessMchId, Long paymentMchId, String useScenes) {
        Optional<Account> accountOpt = accountService.getAccount(userId, accessMchId, paymentMchId, useScenes);
        if (accountOpt.isPresent()) {
            return accountOpt;
        }
        String accountNo = codeService.getAccountNo();

        // 如果没有账户。默认方案: 新增一个账户
        CreateAccountDto dto = new CreateAccountDto();
        dto.setPaymentMchId(paymentMchId);
        dto.setAccessMchId(accessMchId);
        dto.setUseScenes(useScenes);
        dto.setUserId(userId);
        dto.setAccount(accountNo);
        dto.setMchAccount(String.valueOf(userId));
        return Optional.ofNullable(accountService.createAccount(dto));
    }

    @Override
    public void payResult(PaymentMchType paymentMchType, String paymentMchCode, String ro) {
        // 1. 验证签名 & 验证支付单据 & 验证支付金额
        Optional<PayRecord> payRecordOpt = Rules.create()
            // 支付宝
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> alipayMchService.getTradeWithPayRecord(paymentMchCode, ro)))
            // 微信
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> wxpayMchService.getTradeWithPayRecord(paymentMchCode, ro)))
            .of(paymentMchType);
        PayRecord payRecord = payRecordOpt.orElseThrow(UnsupportedOperationException::new);

        Long tradeOrderId = payRecord.getTradeOrderId();
        TradeOrder tradeOrder = Assert.notNull(tradeOrderService.getById(tradeOrderId));

        // 2. 修改交易单状态 & 支付信息
        TradeOrder updateTradeOrder = new TradeOrder();
        updateTradeOrder.setPayType(payRecord.getPayType());
        updateTradeOrder.setPayActId(payRecord.getPayActId());
        updateTradeOrder.setPaymentMchId(payRecord.getPaymentMchId());
        updateTradeOrder.setPayRecordId(payRecord.getId());
        tradeOrderService.updatePaySuccess(tradeOrderId, updateTradeOrder);

        // 3. 新增账户流水
        accountFlowService.createTradeFlow(tradeOrderId);

        // 4. 创建通知任务 & 通知接入商户
        if (StrUtil.isNotBlank(tradeOrder.getNotifyUrl())) {
            syncAccessMchTaskService.createPayed(tradeOrderId);
        }
    }
}
