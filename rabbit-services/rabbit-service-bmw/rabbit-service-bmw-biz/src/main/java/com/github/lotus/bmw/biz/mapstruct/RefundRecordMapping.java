package com.github.lotus.bmw.biz.mapstruct;

import com.github.lotus.bmw.api.pojo.ro.GoRefundRo;
import com.github.lotus.bmw.api.pojo.vo.RefundStatusSyncVo;
import com.github.lotus.bmw.biz.entity.RefundRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface RefundRecordMapping {
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "finishedAt", ignore = true)
    @Mapping(target = "tradeOrderId", ignore = true)
    @Mapping(target = "orderNo", ignore = true)
    @Mapping(target = "accessMchId", ignore = true)
    @Mapping(target = "notifyUrl", ignore = true)
    @Mapping(target = "reason", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    RefundRecord asRefundRecord(GoRefundRo ro);

    RefundStatusSyncVo asRefundSyncVo(RefundRecord entity);
}
