package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.mapstruct.OrderMaintainMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainComplexVo;
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

    public OrderMaintainComplexVo asOrderMaintainComplexVo(OrderMaintain entity) {
        return mapping.asOrderMaintainComplexVo(entity);
    }
}
