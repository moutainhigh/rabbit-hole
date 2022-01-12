package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.mall.biz.entity.OrderItemSku;
import in.hocg.rabbit.mall.biz.mapper.OrderItemSkuMapper;
import in.hocg.rabbit.mall.biz.service.OrderItemSkuService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * [订单模块] 订单详情(商品)表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderItemSkuServiceImpl extends AbstractServiceImpl<OrderItemSkuMapper, OrderItemSku> implements OrderItemSkuService {

    @Override
    public List<OrderItemSku> listByOrderItemId(List<Long> orderItemIds) {
        if (CollUtil.isEmpty(orderItemIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(OrderItemSku::getOrderItemId, orderItemIds).list();
    }
}
