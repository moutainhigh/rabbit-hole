package com.github.lotus.bmw.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.github.lotus.bmw.api.pojo.ro.GetRefundRo;
import com.github.lotus.bmw.api.pojo.ro.GoRefundRo;
import com.github.lotus.bmw.api.pojo.vo.RefundSyncVo;
import com.github.lotus.bmw.biz.cache.BmwCacheService;
import com.github.lotus.bmw.biz.entity.AccessMch;
import com.github.lotus.bmw.biz.entity.RefundRecord;
import com.github.lotus.bmw.biz.mapper.RefundRecordMapper;
import com.github.lotus.bmw.biz.mapstruct.RefundRecordMapping;
import com.github.lotus.bmw.biz.service.PaymentMchDockingService;
import com.github.lotus.bmw.biz.service.RefundRecordService;
import com.github.lotus.bmw.biz.service.TradeOrderService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.web.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [支付模块] 退款记录表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RefundRecordServiceImpl extends AbstractServiceImpl<RefundRecordMapper, RefundRecord> implements RefundRecordService {
    private final BmwCacheService cacheService;
    private final TradeOrderService tradeOrderService;
    private final PaymentMchDockingService dockingService;
    private final RefundRecordMapping mapping;

    @Override
    public RefundSyncVo getRefund(GetRefundRo ro) {
        AccessMch accessMch = cacheService.getAccessMchByEncoding(ro.getAccessCode());
        Assert.notNull(accessMch, "接入应用不存在");
        RefundRecord entity = this.getByAccessMchIdAndOutOrderNoOrOrderNo(accessMch.getId(), ro.getOutOrderNo(), ro.getOrderNo())
            .orElseThrow(() -> ServiceException.wrap("未找到退款单据"));
        return this.convertRefundSyncVo(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RefundSyncVo goRefund(GoRefundRo ro) {
        return dockingService.goRefund(ro);
    }

    @Override
    public boolean updateByIdAndStatus(@NonNull RefundRecord update, @NonNull Long refundRecordId, @NonNull String... refundStatus) {
        return lambdaUpdate().eq(RefundRecord::getId, refundRecordId).in(RefundRecord::getStatus, Lists.newArrayList(refundStatus)).update(update);
    }

    @Override
    public RefundSyncVo convertRefundSyncVo(RefundRecord entity) {
        return mapping.asRefundSyncVo(entity);
    }

    @Override
    public Optional<RefundRecord> getByAccessMchIdAndOutOrderNoOrOrderNo(Long accessMchId, String outOrderNo, String orOrderNo) {
        return this.lambdaQuery().eq(RefundRecord::getAccessMchId, accessMchId)
            .and(wrapper -> wrapper.eq(Strings.isNotBlank(outOrderNo), RefundRecord::getOutOrderNo, outOrderNo).or().eq(Strings.isNotBlank(orOrderNo), RefundRecord::getOrderNo, orOrderNo))
            .oneOpt();
    }
}
