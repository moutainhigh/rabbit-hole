package in.hocg.rabbit.bmw.biz.mapstruct;

import in.hocg.rabbit.bmw.api.pojo.ro.CreateTradeRo;
import in.hocg.rabbit.bmw.api.pojo.vo.TradeStatusSyncVo;
import in.hocg.rabbit.bmw.biz.entity.TradeOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface TradeOrderMapping {
    @Mapping(target = "tradeNo", ignore = true)
    @Mapping(target = "payRecordId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "refundAmt", ignore = true)
    @Mapping(target = "reason", ignore = true)
    @Mapping(target = "realPayAmt", ignore = true)
    @Mapping(target = "paymentMchId", ignore = true)
    @Mapping(target = "payType", ignore = true)
    @Mapping(target = "payActId", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guaranteeFlag", ignore = true)
    @Mapping(target = "finishedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "accessMchId", ignore = true)
    TradeOrder asTradeOrder(CreateTradeRo ro);

    TradeStatusSyncVo asTradeSyncVo(TradeOrder entity);
}
