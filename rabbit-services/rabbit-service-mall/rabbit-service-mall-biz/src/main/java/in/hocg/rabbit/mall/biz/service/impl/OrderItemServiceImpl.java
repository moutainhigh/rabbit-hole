package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.entity.OrderItemSku;
import in.hocg.rabbit.mall.biz.mapper.OrderItemMapper;
import in.hocg.rabbit.mall.biz.mapstruct.OrderItemMapping;
import in.hocg.rabbit.mall.biz.mapstruct.OrderItemSkuMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.CommentClientRo;
import in.hocg.rabbit.mall.biz.pojo.ro.MaintainClientRo;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderItemComplexVo;
import in.hocg.rabbit.mall.biz.service.*;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mall.biz.state.orderitem.OrderItemMaintainStateMachine;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private final OrderMaintainService orderMaintainService;
    private final OrderService orderService;
    private final OrderItemSkuService orderItemSkuService;
    private final SkuService skuService;
    private final OrderItemSkuMapping orderItemSkuMapping;

    @Override
    public List<OrderItemComplexVo> listComplexByOrderId(Long orderId) {
        return LangUtils.toList(this.listByOrderId(orderId), mapping::asOrderItemComplexVo);
    }

    @Override
    public List<OrderItem> listByOrderId(Long orderId) {
        return lambdaQuery().eq(OrderItem::getOrderId, orderId).list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void maintainByBuyer(Long id, MaintainClientRo ro) {
        OrderItem entity = this.getByIdAndOwnerUserId(id, ro.getOperatorId()).orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        OrderItemMaintainStateMachine.apply(entity);
        orderMaintainService.create(id, ro);
    }

    @Override
    public void commentByBuyer(Long id, CommentClientRo ro) {
        OrderItem entity = this.getByIdAndOwnerUserId(id, ro.getOperatorId()).orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        throw new UnsupportedOperationException("暂不支持评价");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrderItemByOrderId(Long orderId, List<CalcOrderVo.OrderItem> items) {
        List<OrderItem> orderItems = Lists.newArrayList();
        List<OrderItemSku> orderItemSkus = Lists.newArrayList();
        for (CalcOrderVo.OrderItem item : items) {
            long id = IdWorker.getId();
            OrderItem orderItem = mapping.asOrderItem(item).setOrderId(orderId);
            OrderItemSku orderItemSku = orderItemSkuMapping.asOrderItemSku(item).setOrderItemId(id)
                .setSkuSpecData(JSONUtil.toJsonStr(item.getSpec()));
            orderItem.setId(id);
            orderItemSku.setId(id);

            Long skuId = orderItemSku.getSkuId();
            Integer quantity = orderItem.getQuantity();
            if (skuService.casValidAndSubtractStock(skuId, quantity)) {
                orderItems.add(orderItem);
                orderItemSkus.add(orderItemSku);
            } else {
                throw ServiceException.wrap("库存商品不足");
            }
        }
        this.saveBatch(orderItems);
        orderItemSkuService.saveBatch(orderItemSkus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundByOrderId(Long orderId) {
        final List<OrderItem> orderItems = this.listByOrderId(orderId);
        List<Long> orderItemIds = LangUtils.toList(orderItems, OrderItem::getId);
        Map<Long, OrderItemSku> itemMaps = LangUtils.toMap(orderItemSkuService.listByOrderItemId(orderItemIds), OrderItemSku::getOrderItemId);
        for (OrderItem orderItem : orderItems) {
            OrderItemSku orderItemSku = itemMaps.get(orderItem.getId());
            final Long skuId = orderItemSku.getSkuId();
            final Integer quantity = orderItem.getQuantity();
            if (!skuService.casValidAndPlusStock(skuId, quantity)) {
                throw ServiceException.wrap("系统繁忙，请稍后");
            }
        }
    }

    public Optional<OrderItem> getByIdAndOwnerUserId(Long id, Long ownerUserId) {
        return baseMapper.getByIdAndOwnerUserId(id, ownerUserId);
    }
}
