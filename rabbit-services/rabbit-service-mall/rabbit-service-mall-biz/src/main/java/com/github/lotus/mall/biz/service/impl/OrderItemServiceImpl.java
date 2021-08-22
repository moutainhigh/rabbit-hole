package com.github.lotus.mall.biz.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.github.lotus.mall.biz.entity.OrderItem;
import com.github.lotus.mall.biz.mapper.OrderItemMapper;
import com.github.lotus.mall.biz.mapstruct.OrderItemMapping;
import com.github.lotus.mall.biz.pojo.vo.OrderItemComplexVo;
import com.github.lotus.mall.biz.service.OrderItemService;
import com.github.lotus.mall.biz.service.OrderRefundApplyService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
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
