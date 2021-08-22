package com.github.lotus.mall.biz.mapstruct;

import com.github.lotus.mall.biz.entity.OrderItem;
import com.github.lotus.mall.biz.pojo.vo.OrderItemComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapping {
    @Mapping(target = "refundApplyItems", ignore = true)
    @Mapping(target = "spec", ignore = true)
    OrderItemComplexVo asOrderItemComplexVo(OrderItem entity);
}
