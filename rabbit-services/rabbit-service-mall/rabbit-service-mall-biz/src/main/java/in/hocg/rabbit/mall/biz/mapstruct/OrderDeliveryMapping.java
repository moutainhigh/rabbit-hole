package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import in.hocg.rabbit.mall.biz.entity.OrderDelivery;
import in.hocg.rabbit.mall.biz.pojo.ro.ShippedOrderBySellerRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface OrderDeliveryMapping {
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "deliveryTel", source = "tel")
    @Mapping(target = "deliveryRegion", source = "region")
    @Mapping(target = "deliveryProvince", source = "province")
    @Mapping(target = "deliveryPostcode", source = "postcode")
    @Mapping(target = "deliveryName", source = "name")
    @Mapping(target = "deliveryCity", source = "city")
    @Mapping(target = "deliveryAddress", source = "address")
    @Mapping(target = "deliveryAdcode", source = "adcode")
    OrderDelivery asOrderDelivery(UserAddressFeignVo address);

    @Mapping(target = "encoding", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    OrderDelivery asOrderDelivery(ShippedOrderBySellerRo ro);

    OrderDeliveryOrdinaryVo asOrderDeliveryOrdinaryVo(OrderDelivery entity);

    @Mapping(target = "orderNo", ignore = true)
    OrderDeliveryComplexVo asOrderDeliveryComplexVo(OrderDelivery entity);
}
