package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.mapstruct.OrderMaintainMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderItemComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderMaintainConvert {
    private final OrderMaintainMapping mapping;
    private final OrderItemService orderItemService;

    public OrderMaintainComplexVo asOrderMaintainComplexVo(OrderMaintain entity) {
        OrderItem orderItem = orderItemService.getById(entity.getOrderItemId());
        return mapping.asOrderMaintainComplexVo(entity)
            .setOrderItem(orderItemService.as(orderItem, OrderItemComplexVo.class));
    }

    public OrderMaintainOrdinaryVo asOrderMaintainOrdinaryVo(OrderMaintain entity) {
        return mapping.asOrderMaintainOrdinaryVo(entity);
    }
}
