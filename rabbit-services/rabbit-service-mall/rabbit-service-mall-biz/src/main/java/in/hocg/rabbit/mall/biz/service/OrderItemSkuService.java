package in.hocg.rabbit.mall.biz.service;

import in.hocg.rabbit.mall.biz.entity.OrderItemSku;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [订单模块] 订单详情(商品)表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
public interface OrderItemSkuService extends AbstractService<OrderItemSku> {

    List<OrderItemSku> listByOrderItemId(List<Long> orderItemIds);
}
