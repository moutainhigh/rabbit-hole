package in.hocg.rabbit.mall.biz.service;

import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderItemComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [订单模块] 订单商品表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface OrderItemService extends AbstractService<OrderItem> {

    List<OrderItemComplexVo> listComplexByOrderId(Long orderId);

    List<OrderItem> listByOrderId(Long orderId);
}
