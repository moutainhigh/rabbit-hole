package in.hocg.rabbit.mall.biz.convert;

import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.mapstruct.OrderMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderItemComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderConvert {
    private final OrderMapping mapping;
    private final OrderItemService orderItemService;

    public OrderComplexVo asOrderComplexVo(Order entity) {
        Long entityId = entity.getId();

        OrderComplexVo result = mapping.asOrderComplexVo(entity);
        result.setOrderItems(LangUtils.toList(orderItemService.listByOrderId(entityId),
            item -> orderItemService.as(item, OrderItemComplexVo.class)));
        return result;
    }

    public OrderOrdinaryVo asOrderOrdinaryVo(Order entity) {
        return mapping.asOrderOrdinaryVo(entity);
    }
}
