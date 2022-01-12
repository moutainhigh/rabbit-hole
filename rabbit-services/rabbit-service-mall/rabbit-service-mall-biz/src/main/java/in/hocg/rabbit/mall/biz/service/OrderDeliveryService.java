package in.hocg.rabbit.mall.biz.service;

import in.hocg.rabbit.mall.biz.entity.OrderDelivery;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.pojo.ro.ShippedOrderBySellerRo;

/**
 * <p>
 * [订单模块] 配送单 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
public interface OrderDeliveryService extends AbstractService<OrderDelivery> {

    void create(Long orderId, ShippedOrderBySellerRo ro);
}
