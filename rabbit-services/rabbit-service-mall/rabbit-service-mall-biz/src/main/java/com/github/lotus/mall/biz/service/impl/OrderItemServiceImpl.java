package com.github.lotus.mall.biz.service.impl;

import com.github.lotus.mall.biz.entity.OrderItem;
import com.github.lotus.mall.biz.mapper.OrderItemMapper;
import com.github.lotus.mall.biz.service.OrderItemService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

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

}
