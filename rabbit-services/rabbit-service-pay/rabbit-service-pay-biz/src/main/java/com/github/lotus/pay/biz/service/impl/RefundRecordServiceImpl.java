package com.github.lotus.pay.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.pay.biz.entity.RefundRecord;
import com.github.lotus.pay.biz.mapper.RefundRecordMapper;
import com.github.lotus.pay.biz.mapstruct.RefundRecordMapping;
import com.github.lotus.pay.biz.pojo.ro.RefundRecordPagingRo;
import com.github.lotus.pay.biz.pojo.vo.RefundRecordOrdinaryVo;
import com.github.lotus.pay.biz.service.RefundRecordService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 退款记录表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RefundRecordServiceImpl extends AbstractServiceImpl<RefundRecordMapper, RefundRecord> implements RefundRecordService {
    private final RefundRecordMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<RefundRecord> getByRefundSn(String refundSn) {
        return lambdaQuery().eq(RefundRecord::getRefundSn, refundSn).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateByIdAndRefundStatus(RefundRecord update, Long refundRecordId, String... refundStatus) {
        return lambdaUpdate().eq(RefundRecord::getId, refundRecordId).in(RefundRecord::getRefundStatus, refundStatus).update(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<RefundRecordOrdinaryVo> pagingByTradeId(Long tradeId, RefundRecordPagingRo ro) {
        return baseMapper.pagingByTradeId(tradeId, ro, ro.ofPage()).convert(this::convertOrdinary);
    }

    private RefundRecordOrdinaryVo convertOrdinary(RefundRecord entity) {
        return mapping.asOrdinary(entity);
    }
}
