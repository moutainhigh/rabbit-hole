package com.github.lotus.bmw.biz.mapstruct;

import com.github.lotus.bmw.biz.entity.PaymentMchRecord;
import com.github.lotus.bmw.biz.pojo.dto.PaymentMchRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PaymentMchRecordMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    PaymentMchRecord asPaymentMchRecord(PaymentMchRecordDto dto);
}
