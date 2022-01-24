package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.entity.OrderDelivery;
import in.hocg.rabbit.mall.biz.mapstruct.OrderDeliveryMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderDeliveryConvert {
    private final OrderDeliveryMapping mapping;
    private final OrderService orderService;

    public OrderDeliveryOrdinaryVo asOrderDeliveryOrdinaryVo(OrderDelivery entity) {
        return mapping.asOrderDeliveryOrdinaryVo(entity);
    }

    public OrderDeliveryComplexVo asOrderDeliveryComplexVo(OrderDelivery entity) {
        Long orderId = entity.getOrderId();
        Order order = orderService.getById(orderId);
        return mapping.asOrderDeliveryComplexVo(entity)
            .setOrderNo(order.getEncoding());
    }
}
