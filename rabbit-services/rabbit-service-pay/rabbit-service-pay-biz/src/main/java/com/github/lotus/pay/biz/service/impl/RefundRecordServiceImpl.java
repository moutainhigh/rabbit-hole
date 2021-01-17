package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.RefundRecord;
import com.github.lotus.pay.biz.mapper.RefundRecordMapper;
import com.github.lotus.pay.biz.service.RefundRecordService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 退款记录表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RefundRecordServiceImpl extends AbstractServiceImpl<RefundRecordMapper, RefundRecord> implements RefundRecordService {

    @Override
    public Optional<RefundRecord> selectOneByRefundSn(String refundSn) {
        return lambdaQuery().eq(RefundRecord::getRefundSn, refundSn).oneOpt();
    }

    @Override
    public boolean updateOneByIdAndTradeStatus(RefundRecord update, Long id, Integer refundStatus) {
        return lambdaUpdate().eq(RefundRecord::getId, id)
            .eq(RefundRecord::getRefundStatus, refundStatus).update(update);
    }
}
