package in.hocg.rabbit.mall.biz.service.impl;

import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.mapper.OrderItemMapper;
import in.hocg.rabbit.mall.biz.mapstruct.OrderItemMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderItemComplexVo;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import in.hocg.rabbit.mall.biz.service.OrderRefundApplyService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [订单模块] 订单商品表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderItemServiceImpl extends AbstractServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    private final OrderItemMapping mapping;
    private final OrderRefundApplyService orderRefundApplyService;

    @Override
    public List<OrderItemComplexVo> listComplexByOrderId(Long orderId) {
        return LangUtils.toList(this.listByOrderId(orderId), this::convertComplex);
    }

    @Override
    public List<OrderItem> listByOrderId(Long orderId) {
        return lambdaQuery().eq(OrderItem::getOrderId, orderId).list();
    }

    private OrderItemComplexVo convertComplex(OrderItem entity) {
        OrderItemComplexVo result = mapping.asOrderItemComplexVo(entity);
        result.setRefundApplyItems(orderRefundApplyService.listComplexByOrderItemId(entity.getId()));
        return result;
    }
}
