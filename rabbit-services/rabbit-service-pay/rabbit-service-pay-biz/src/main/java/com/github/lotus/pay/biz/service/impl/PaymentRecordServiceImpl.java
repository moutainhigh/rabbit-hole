package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PaymentRecord;
import com.github.lotus.pay.biz.mapper.PaymentRecordMapper;
import com.github.lotus.pay.biz.service.PaymentRecordService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * [支付网关] 支付记录表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentRecordServiceImpl extends AbstractServiceImpl<PaymentRecordMapper, PaymentRecord> implements PaymentRecordService {

    @Override
    public List<PaymentRecord> selectListByTradeId(Long tradeId) {
        return lambdaQuery().eq(PaymentRecord::getTradeId, tradeId).list();
    }
}
