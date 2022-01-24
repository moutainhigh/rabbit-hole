package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.OrderDelivery;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryOrdinaryVo;
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
    public OrderDeliveryOrdinaryVo asOrderDeliveryOrdinaryVo(OrderDelivery entity) {
        return null;
    }

    public OrderDeliveryComplexVo asOrderDeliveryComplexVo(OrderDelivery entity) {
        return null;
    }
}
