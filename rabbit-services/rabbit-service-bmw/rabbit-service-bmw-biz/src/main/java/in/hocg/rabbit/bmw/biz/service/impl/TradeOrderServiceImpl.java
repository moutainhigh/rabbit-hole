package in.hocg.rabbit.bmw.biz.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.bmw.api.pojo.ro.CloseTradeRo;
import in.hocg.rabbit.bmw.api.pojo.ro.CreateTradeRo;
import in.hocg.rabbit.bmw.api.pojo.ro.GetTradeRo;
import in.hocg.rabbit.bmw.biz.entity.*;
import in.hocg.rabbit.bmw.biz.pojo.ro.GoPayRo;
import in.hocg.rabbit.bmw.biz.pojo.vo.GoPayVo;
import in.hocg.rabbit.bmw.api.pojo.vo.TradeStatusSyncVo;
import in.hocg.rabbit.bmw.biz.cache.BmwCacheService;
import in.hocg.rabbit.bmw.biz.constant.LockKeys;
import in.hocg.rabbit.bmw.biz.mapper.TradeOrderMapper;
import in.hocg.rabbit.bmw.biz.mapstruct.TradeOrderMapping;
import in.hocg.rabbit.bmw.biz.pojo.dto.CreatePayRecordDto;
import in.hocg.rabbit.bmw.biz.service.*;
import in.hocg.rabbit.common.datadict.bmw.TradeOrderStatus;
import com.google.common.collect.Lists;
import in.hocg.boot.distributed.lock.autoconfiguration.annotation.LockKey;
import in.hocg.boot.distributed.lock.autoconfiguration.annotation.UseLock;
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
    private final SyncAccessMchTaskService syncAccessMchTaskService;
    private final AccountFlowService accountFlowService;
    private final PaymentMchDockingService dockingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TradeStatusSyncVo createTrade(CreateTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        LocalDateTime now = LocalDateTime.now();
        String orderNo = codeService.getTradeOrderNo();

        TradeOrder entity = mapping.asTradeOrder(ro);
        entity.setPlanCloseAt(LangUtils.getOrDefault(entity.getPlanCloseAt(), now.plusDays(999L)));
        entity.setAccessMchId(accessMch.getId());
        entity.setStatus(TradeOrderStatus.Processing.getCodeStr());
        entity.setTradeNo(orderNo);
        entity.setCreatedAt(now);
        Assert.isTrue(this.validInsert(entity));
        return this.convertTradeSyncVo(getById(entity.getId()));
    }

    @Override
    public TradeStatusSyncVo getTrade(GetTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        TradeOrder tradeOrder = this.getByAccessMchIdAndOutTradeNoOrTradeNo(accessMch.getId(), ro.getOutTradeNo(), ro.getTradeNo())
            .orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        return this.convertTradeSyncVo(tradeOrder);
    }

    @Override
    public TradeStatusSyncVo getTradeById(Long tradeOrderId) {
        return this.convertTradeSyncVo(getById(tradeOrderId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TradeStatusSyncVo closeTrade(CloseTradeRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        TradeOrder tradeOrder = this.getByAccessMchIdAndOutTradeNoOrTradeNo(accessMch.getId(), ro.getOutTradeNo(), ro.getTradeNo())
            .orElseThrow(() -> ServiceException.wrap("未找到交易单据"));

        Long tradeOrderId = tradeOrder.getId();
        this.closeTrade(tradeOrderId);
        return this.convertTradeSyncVo(getById(tradeOrderId));
    }

    @Override
    public void closeTrade(Long tradeOrderId) {
        LocalDateTime now = LocalDateTime.now();
        TradeOrder tradeOrder = getById(tradeOrderId);

        // 如果是初始化状态可直接关闭
        boolean isProcessing = TradeOrderStatus.Processing.eq(tradeOrder.getStatus());
        Assert.isTrue(isProcessing, "交易单据状态错误");

        TradeOrder update = new TradeOrder().setFinishedAt(now).setStatus(TradeOrderStatus.Closed.getCodeStr()).setLastUpdatedAt(now);
        boolean isOk = this.updateByIdAndStatus(update, tradeOrderId, TradeOrderStatus.Processing.getCodeStr());
        ValidUtils.isTrue(isOk, "系统繁忙，关单失败");
    }

    private TradeStatusSyncVo convertTradeSyncVo(TradeOrder entity) {
        return mapping.asTradeSyncVo(entity);
    }

    private boolean updateByIdAndStatus(@NonNull TradeOrder update, @NonNull Long tradeOrderId, @NonNull String... tradeStatus) {
        return lambdaUpdate().eq(TradeOrder::getId, tradeOrderId).in(TradeOrder::getStatus, Lists.newArrayList(tradeStatus)).update(update);
    }

    @Override
    public Optional<TradeOrder> getByAccessMchIdAndOutTradeNoOrTradeNo(Long accessMchId, String outOrderNo, String orOrderNo) {
        return this.lambdaQuery().eq(TradeOrder::getAccessMchId, accessMchId)
            .and(wrapper -> wrapper.eq(Strings.isNotBlank(outOrderNo), TradeOrder::getOutTradeNo, outOrderNo).or().eq(Strings.isNotBlank(orOrderNo), TradeOrder::getTradeNo, orOrderNo))
            .oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoPayVo goPay(GoPayRo ro) {
        Long tradeOrderId = ro.getTradeOrderId();
        TradeOrder tradeOrder = this.getById(tradeOrderId);
        Assert.notNull(tradeOrder, "未找到交易单据");
        Assert.isTrue(TradeOrderStatus.Processing.eq(tradeOrder.getStatus()), "交易单据状态异常");
        Long accessMchId = tradeOrder.getAccessMchId();

        // 1. 检查支付类型是否被支持
        PaymentMch paymentMch = paymentMchService.getByAccessMchIdAndPaySceneIdAndPayType(accessMchId, ro.getPaySceneId(), ro.getPayType())
            .orElseThrow(() -> ServiceException.wrap("支付类型[{}]未匹配到接入商户支持的支付商户", ro.getPayType()));
        Long paymentMchId = paymentMch.getId();
        ro.setPaymentMchId(paymentMchId);

        // 2. 获取对应的支付账户
        Account account = dockingService.getAccount(ro.getUserId(), accessMchId, paymentMchId, ro.getUseScenes())
            .orElseThrow(() -> ServiceException.wrap("未找到对应的支付账户"));

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
        return this.lambdaQuery().eq(TradeOrder::getTradeNo, orderNo).oneOpt();
    }

    @Override
    @UseLock(key = LockKeys.PAY_SUCCESS)
    public void paySuccess(@LockKey Long payRecordId) {
        PayRecord payRecord = payRecordService.getById(payRecordId);
        Long tradeOrderId = payRecord.getTradeOrderId();
        TradeOrder tradeOrder = this.getById(tradeOrderId);

        // 2. 修改交易单状态 & 支付信息
        TradeOrder updateTradeOrder = new TradeOrder();
        updateTradeOrder.setPayType(payRecord.getPayType());
        updateTradeOrder.setPayActId(payRecord.getPayActId());
        updateTradeOrder.setPaymentMchId(payRecord.getPaymentMchId());
        updateTradeOrder.setPayRecordId(payRecord.getId());
        this.updatePaySuccess(tradeOrderId, updateTradeOrder);

        // 3. 新增账户流水
        accountFlowService.createTradeFlow(tradeOrderId);

        // 4. 创建通知任务 & 通知接入商户
        if (StrUtil.isNotBlank(tradeOrder.getNotifyUrl())) {
            syncAccessMchTaskService.createPayed(tradeOrderId);
        }
    }

}
