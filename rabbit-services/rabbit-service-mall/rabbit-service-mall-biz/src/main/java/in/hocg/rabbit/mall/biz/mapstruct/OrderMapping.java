package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.pojo.ro.UpdateOrderRo;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface OrderMapping {
    @Mapping(target = "tradeStatusName", ignore = true)
    @Mapping(target = "refundStatusName", ignore = true)
    @Mapping(target = "receiveStatusName", ignore = true)
    @Mapping(target = "payStatusName", ignore = true)
    @Mapping(target = "orderStatusName", ignore = true)
    @Mapping(target = "deliveryStatusName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "ownerUserName", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    OrderComplexVo asOrderComplexVo(Order entity);

    @Mapping(target = "ownerUserName", ignore = true)
    OrderOrdinaryVo asOrderOrdinaryVo(Order entity);

    CalcOrderVo.UserAddressVo asCalcOrderVoUserAddressVo(UserAddressFeignVo defAddress);

    @Mapping(target = "adjustmentAmt", ignore = true)
    @Mapping(target = "tradeStatus", ignore = true)
    @Mapping(target = "tradeNo", ignore = true)
    @Mapping(target = "remark", ignore = true)
    @Mapping(target = "refundStatus", ignore = true)
    @Mapping(target = "receiverTel", ignore = true)
    @Mapping(target = "receiverRegion", ignore = true)
    @Mapping(target = "receiverProvince", ignore = true)
    @Mapping(target = "receiverPostcode", ignore = true)
    @Mapping(target = "receiverName", ignore = true)
    @Mapping(target = "receiverCity", ignore = true)
    @Mapping(target = "receiverAddress", ignore = true)
    @Mapping(target = "receiverAdcode", ignore = true)
    @Mapping(target = "receiveStatus", ignore = true)
    @Mapping(target = "receiveAt", ignore = true)
    @Mapping(target = "planCloseAt", ignore = true)
    @Mapping(target = "planReceiveAt", ignore = true)
    @Mapping(target = "payWay", ignore = true)
    @Mapping(target = "payStatus", ignore = true)
    @Mapping(target = "payAt", ignore = true)
    @Mapping(target = "ownerUserId", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finishAt", ignore = true)
    @Mapping(target = "encoding", ignore = true)
    @Mapping(target = "deliveryStatus", ignore = true)
    @Mapping(target = "deliveryAt", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Order asOrder(CalcOrderVo calcResult);

    @Mapping(target = "tradeStatus", ignore = true)
    @Mapping(target = "tradeNo", ignore = true)
    @Mapping(target = "totalSaleAmt", ignore = true)
    @Mapping(target = "totalRealAmt", ignore = true)
    @Mapping(target = "totalPayAmt", ignore = true)
    @Mapping(target = "remark", ignore = true)
    @Mapping(target = "refundStatus", ignore = true)
    @Mapping(target = "receiveStatus", ignore = true)
    @Mapping(target = "receiveAt", ignore = true)
    @Mapping(target = "planReceiveAt", ignore = true)
    @Mapping(target = "planCloseAt", ignore = true)
    @Mapping(target = "payWay", ignore = true)
    @Mapping(target = "payStatus", ignore = true)
    @Mapping(target = "payAt", ignore = true)
    @Mapping(target = "ownerUserId", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finishAt", ignore = true)
    @Mapping(target = "expressAmt", ignore = true)
    @Mapping(target = "encoding", ignore = true)
    @Mapping(target = "discountAmt", ignore = true)
    @Mapping(target = "deliveryStatus", ignore = true)
    @Mapping(target = "deliveryAt", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "adjustmentAmt", ignore = true)
    Order asOrder(UpdateOrderRo ro);
}
