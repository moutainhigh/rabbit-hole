package in.hocg.rabbit.mall.biz.service;

import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.pojo.ro.CommentClientRo;
import in.hocg.rabbit.mall.biz.pojo.ro.MaintainClientRo;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderItemComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

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

    void maintainByBuyer(Long id, MaintainClientRo ro);

    void commentByBuyer(Long id, CommentClientRo ro);

    void saveOrderItemByOrderId(Long orderId, List<CalcOrderVo.OrderItem> items);

    void refundByOrderId(Long orderId);
}
