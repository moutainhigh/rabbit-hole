package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PayRecord;
import com.github.lotus.pay.biz.mapper.PayRecordMapper;
import com.github.lotus.pay.biz.service.PayRecordService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [支付网关] 支付记录表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PayRecordServiceImpl extends AbstractServiceImpl<PayRecordMapper, PayRecord> implements PayRecordService {

    @Override
    public List<PayRecord> listByTradeId(Long tradeId) {
        return lambdaQuery().eq(PayRecord::getTradeId, tradeId).list();
    }
}
