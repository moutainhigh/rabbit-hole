package in.hocg.rabbit.mall.biz.convert;

import com.alibaba.fastjson.JSON;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.entity.OrderItemSku;
import in.hocg.rabbit.mall.biz.mapstruct.OrderItemMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderItemComplexVo;
import in.hocg.rabbit.mall.biz.service.OrderItemSkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderItemConvert {
    private final OrderItemMapping mapping;
    private final OrderItemSkuService orderItemSkuService;

    public OrderItemComplexVo asOrderItemComplexVo(OrderItem entity) {
        Long entityId = entity.getId();
        OrderItemSku orderItemSku = orderItemSkuService.getById(entityId);
        String specData = orderItemSku.getSkuSpecData();
        return mapping.asOrderItemComplexVo(entity)
            .setSpec(JSON.parseArray(specData, KeyValue.class))
            .setImageUrl(orderItemSku.getImageUrl())
            .setSkuCode(orderItemSku.getSkuCode())
            .setSkuId(orderItemSku.getSkuId());
    }
}
