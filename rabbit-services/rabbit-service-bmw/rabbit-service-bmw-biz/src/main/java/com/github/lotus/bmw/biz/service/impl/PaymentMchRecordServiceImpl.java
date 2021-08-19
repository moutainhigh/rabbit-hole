package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PaymentMchRecord;
import com.github.lotus.bmw.biz.mapper.PaymentMchRecordMapper;
import com.github.lotus.bmw.biz.mapstruct.PaymentMchRecordMapping;
import com.github.lotus.bmw.biz.pojo.dto.PaymentMchRecordDto;
import com.github.lotus.bmw.biz.service.PaymentMchRecordService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * [支付模块] 支付商户业务记录表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentMchRecordServiceImpl extends AbstractServiceImpl<PaymentMchRecordMapper, PaymentMchRecord>
    implements PaymentMchRecordService {
    private final PaymentMchRecordMapping mapping;

    @Override
    public PaymentMchRecord create(PaymentMchRecordDto dto) {
        PaymentMchRecord entity = mapping.asPaymentMchRecord(dto);
        entity.setCreatedAt(LocalDateTime.now());
        this.validInsert(entity);
        return entity;
    }
}
