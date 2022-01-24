package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderItemComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapping {
    @Mapping(target = "maintainStatusName", ignore = true)
    @Mapping(target = "commentStatusName", ignore = true)
    @Mapping(target = "skuId", ignore = true)
    @Mapping(target = "skuCode", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "spec", ignore = true)
    OrderItemComplexVo asOrderItemComplexVo(OrderItem entity);

    @Mapping(target = "commentedAt", ignore = true)
    @Mapping(target = "commentStatus", ignore = true)
    @Mapping(target = "planMaintainAt", ignore = true)
    @Mapping(target = "maintainStatus", ignore = true)
    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    OrderItem asOrderItem(CalcOrderVo.OrderItem item);
}
