package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.OrderItemSku;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface OrderItemSkuMapping {
    @Mapping(target = "skuSpecData", ignore = true)
    @Mapping(target = "skuCode", ignore = true)
    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    OrderItemSku asOrderItemSku(CalcOrderVo.OrderItem item);
}
