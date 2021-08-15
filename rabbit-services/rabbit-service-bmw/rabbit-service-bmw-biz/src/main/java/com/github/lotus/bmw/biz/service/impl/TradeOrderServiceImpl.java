package com.github.lotus.bmw.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.github.lotus.bmw.api.pojo.ro.CloseTradeRo;
import com.github.lotus.bmw.api.pojo.ro.CreateTradeRo;
import com.github.lotus.bmw.api.pojo.ro.GetTradeRo;
import com.github.lotus.bmw.api.pojo.ro.PayTradeRo;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.api.pojo.vo.TradeSyncVo;
import com.github.lotus.bmw.biz.cache.BmwCacheService;
import com.github.lotus.bmw.biz.entity.*;
import com.github.lotus.bmw.biz.mapper.TradeOrderMapper;
import com.github.lotus.bmw.biz.mapstruct.TradeOrderMapping;
import com.github.lotus.bmw.biz.pojo.dto.CreatePayRecordDto;
import com.github.lotus.bmw.biz.service.*;
import com.github.lotus.common.datadict.bmw.TradeOrderStatus;
import com.github.lotus.bmw.biz.service.SNCodeService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [支付模块] 交易单表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TradeOrderServiceImpl extends AbstractServiceImpl<TradeOrderMapper, TradeOrder>
    implements TradeOrderService {
    private final SNCodeService codeService;
    private final BmwCacheService cacheService;
    private final TradeOrderMapping mapping;
    private final PayRecordService payRecordService;
    private final PaymentMchService paymentMchService;
    private final AccessMchService accessMchService;
    private final AccountService accountService;
    private final AccountFlowService accountFlowService;
    private final PaymentMchDockingService dockingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TradeSyncVo createTrade(CreateTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        LocalDateTime now = LocalDateTime.now();
        String orderNo = codeService.getTradeOrderNo();

        TradeOrder entity = mapping.asTradeOrder(ro);
        entity.setPlanCloseAt(LangUtils.getOrDefault(entity.getPlanCloseAt(), now.plusDays(999L)));
        entity.setAccessMchId(accessMch.getId());
        entity.setStatus(TradeOrderStatus.Processing.getCodeStr());
        entity.setOrderNo(orderNo);
        entity.setCreatedAt(now);
        Assert.isTrue(this.validInsert(entity));
        return this.convertTradeSyncVo(getById(entity.getId()));
    }

    @Override
    public TradeSyncVo getTrade(GetTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        TradeOrder tradeOrder = this.getByAccessMchIdAndOutOrderNoOrOrderNo(accessMch.getId(), ro.getOutOrderNo(), ro.getOrderNo())
            .orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        return this.convertTradeSyncVo(tradeOrder);
    }

    @Override
    public TradeSyncVo getTradeById(Long tradeOrderId) {
        return this.convertTradeSyncVo(getById(tradeOrderId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TradeSyncVo closeTrade(CloseTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        TradeOrder tradeOrder = this.getByAccessMchIdAndOutOrderNoOrOrderNo(accessMch.getId(), ro.getOutOrderNo(), ro.getOrderNo())
            .orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        LocalDateTime now = LocalDateTime.now();
        Long tradeOrderId = tradeOrder.getId();

        // 如果是初始化状态可直接关闭
        boolean isProcessing = TradeOrderStatus.Processing.eq(tradeOrder.getStatus());
        Assert.isTrue(isProcessing, "交易单据状态错误");

        TradeOrder update = new TradeOrder().setFinishedAt(now).setStatus(TradeOrderStatus.Closed.getCodeStr()).setLastUpdatedAt(now);
        boolean isOk = this.updateByIdAndStatus(update, tradeOrderId, TradeOrderStatus.Processing.getCodeStr());
        ValidUtils.isTrue(isOk, "系统繁忙，关单失败");

        return this.convertTradeSyncVo(getById(tradeOrderId));
    }

    private TradeSyncVo convertTradeSyncVo(TradeOrder entity) {
        return mapping.asTradeSyncVo(entity);
    }

    private boolean updateByIdAndStatus(@NonNull TradeOrder update, @NonNull Long tradeOrderId, @NonNull String... tradeStatus) {
        return lambdaUpdate().eq(TradeOrder::getId, tradeOrderId).in(TradeOrder::getStatus, Lists.newArrayList(tradeStatus)).update(update);
    }

    @Override
    public Optional<TradeOrder> getByAccessMchIdAndOutOrderNoOrOrderNo(Long accessMchId, String outOrderNo, String orOrderNo) {
        return this.lambdaQuery().eq(TradeOrder::getAccessMchId, accessMchId)
            .and(wrapper -> wrapper.eq(Strings.isNotBlank(outOrderNo), TradeOrder::getOutOrderNo, outOrderNo).or().eq(Strings.isNotBlank(orOrderNo), TradeOrder::getOrderNo, orOrderNo))
            .oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayTradeVo goPay(PayTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        Long accessMchId = accessMch.getId();

        // 1. 检查支付类型是否被支持
        PaymentMch paymentMch = paymentMchService.getByAccessMchIdAndSceneCodeAndPayType(accessMchId, ro.getSceneCode(), ro.getPayType())
            .orElseThrow(() -> ServiceException.wrap("支付类型[{}]未匹配到接入商户支持的支付商户", ro.getPayType()));
        Long paymentMchId = paymentMch.getId();

        // 2. 获取对应的支付账户
        Account account = dockingService.getAccount(ro.getUserId(), accessMchId, paymentMchId, ro.getUseScenes())
            .orElseThrow(() -> ServiceException.wrap("未找到对应的支付账户"));

        TradeOrder tradeOrder = this.getByAccessMchIdAndOutOrderNoOrOrderNo(accessMchId, ro.getOutOrderNo(), ro.getOrderNo())
            .orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        Assert.isTrue(TradeOrderStatus.Processing.eq(tradeOrder.getStatus()), "交易单据状态异常");

        // 3. 创建支付记录
        CreatePayRecordDto createPayRecordDto = new CreatePayRecordDto();
        createPayRecordDto.setPayType(ro.getPayType());
        createPayRecordDto.setPaymentMchId(paymentMchId);
        createPayRecordDto.setPayActId(account.getId());
        createPayRecordDto.setTradeOrderId(tradeOrder.getId());
        PayRecord payRecord = Assert.notNull(payRecordService.createPayRecord(createPayRecordDto));

        ro.setPaymentMchType(paymentMch.getType());
        ro.setPayRecordId(payRecord.getId());

        // 4. 发起支付
        return dockingService.goPay(ro);
    }

    @Override
    public void updateRefundAmtById(Long tradeOrderId, BigDecimal newRefundAmt, BigDecimal oldRefundAmt) {
        TradeOrder update = new TradeOrder();
        update.setRefundAmt(newRefundAmt);
        update.setLastUpdatedAt(LocalDateTime.now());
        lambdaUpdate().eq(TradeOrder::getId, tradeOrderId).eq(TradeOrder::getTradeAmt, oldRefundAmt)
            .update(update);
    }

    @Override
    public boolean updatePaySuccess(Long tradeOrderId, TradeOrder update) {
        return lambdaUpdate().eq(TradeOrder::getId, tradeOrderId)
            .eq(TradeOrder::getStatus, TradeOrderStatus.Processing.getCode())
            .update(update);
    }

    @Override
    public Optional<TradeOrder> getByOrderNo(String orderNo) {
        return this.lambdaQuery().eq(TradeOrder::getOrderNo, orderNo).oneOpt();
    }

}
