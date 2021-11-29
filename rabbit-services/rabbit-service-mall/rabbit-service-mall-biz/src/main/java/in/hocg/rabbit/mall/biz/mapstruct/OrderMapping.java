package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface OrderMapping {
    @Mapping(target = "orderItems", ignore = true)
    OrderComplexVo asOrderComplexVo(Order entity);
}
