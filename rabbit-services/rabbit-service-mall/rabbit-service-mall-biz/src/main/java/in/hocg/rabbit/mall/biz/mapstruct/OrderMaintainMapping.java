package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.pojo.ro.MaintainClientRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface OrderMaintainMapping {
    @Mapping(target = "commentStatus", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "refundQuantity", ignore = true)
    @Mapping(target = "refundAmt", ignore = true)
    @Mapping(target = "ownerUserId", ignore = true)
    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "handlerUserId", ignore = true)
    @Mapping(target = "handleRemark", ignore = true)
    @Mapping(target = "handleAt", ignore = true)
    @Mapping(target = "encoding", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    OrderMaintain asOrderMaintain(MaintainClientRo ro);

    @Mapping(target = "typeName", ignore = true)
    @Mapping(target = "statusName", ignore = true)
    @Mapping(target = "ownerUserName", ignore = true)
    @Mapping(target = "handlerUserName", ignore = true)
    @Mapping(target = "commentStatusName", ignore = true)
    OrderMaintainComplexVo asOrderMaintainComplexVo(OrderMaintain entity);

    @Mapping(target = "typeName", ignore = true)
    @Mapping(target = "statusName", ignore = true)
    @Mapping(target = "ownerUserName", ignore = true)
    @Mapping(target = "handlerUserName", ignore = true)
    @Mapping(target = "commentStatusName", ignore = true)
    OrderMaintainOrdinaryVo asOrderMaintainOrdinaryVo(OrderMaintain entity);
}
