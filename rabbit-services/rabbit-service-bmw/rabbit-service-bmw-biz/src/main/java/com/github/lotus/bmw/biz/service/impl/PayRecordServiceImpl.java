package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PayRecord;
import com.github.lotus.bmw.biz.mapper.PayRecordMapper;
import com.github.lotus.bmw.biz.mapstruct.PayRecordMapping;
import com.github.lotus.bmw.biz.pojo.dto.CreatePayRecordDto;
import com.github.lotus.bmw.biz.service.PayRecordService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * [支付模块] 支付记录表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PayRecordServiceImpl extends AbstractServiceImpl<PayRecordMapper, PayRecord> implements PayRecordService {
    private final PayRecordMapping mapping;

    @Override
    public PayRecord createPayRecord(CreatePayRecordDto dto) {
        PayRecord entity = mapping.asPayRecord(dto);
        entity.setCreatedAt(LocalDateTime.now());
        validInsert(entity);
        return entity;
    }
}
