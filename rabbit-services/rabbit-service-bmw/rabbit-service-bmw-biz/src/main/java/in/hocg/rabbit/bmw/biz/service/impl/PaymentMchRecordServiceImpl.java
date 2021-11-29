package in.hocg.rabbit.bmw.biz.service.impl;

import in.hocg.rabbit.bmw.biz.entity.PaymentMchRecord;
import in.hocg.rabbit.bmw.biz.mapper.PaymentMchRecordMapper;
import in.hocg.rabbit.bmw.biz.mapstruct.PaymentMchRecordMapping;
import in.hocg.rabbit.bmw.biz.pojo.dto.PaymentMchRecordDto;
import in.hocg.rabbit.bmw.biz.service.PaymentMchRecordService;
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
