package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.com.api.UserAddressServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import in.hocg.rabbit.mall.biz.entity.OrderDelivery;
import in.hocg.rabbit.mall.biz.mapper.OrderDeliveryMapper;
import in.hocg.rabbit.mall.biz.mapstruct.OrderDeliveryMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.ShippedOrderBySellerRo;
import in.hocg.rabbit.mall.biz.service.OrderDeliveryService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * [订单模块] 配送单 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderDeliveryServiceImpl extends AbstractServiceImpl<OrderDeliveryMapper, OrderDelivery> implements OrderDeliveryService {
    private final OrderItemService orderItemService;
    private final UserAddressServiceApi userAddressServiceApi;
    private final OrderDeliveryMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Long orderId, ShippedOrderBySellerRo ro) {
        Long userAddressId = ro.getUserAddressId();
        UserAddressFeignVo address = Assert.notNull(userAddressServiceApi.getById(userAddressId), "发货地址不存在");

        OrderDelivery entity = mapping.asOrderDelivery(address);
        validInsert(entity);
    }
}
