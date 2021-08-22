package com.github.lotus.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.Order;
import com.github.lotus.mall.biz.mapper.OrderMapper;
import com.github.lotus.mall.biz.pojo.ro.CloseOrderRo;
import com.github.lotus.mall.biz.pojo.ro.OrderPagingRo;
import com.github.lotus.mall.biz.pojo.ro.ShippedOrderRo;
import com.github.lotus.mall.biz.pojo.ro.UpdateOrderRo;
import com.github.lotus.mall.biz.pojo.vo.OrderComplexVo;
import com.github.lotus.mall.biz.service.OrderService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [订单模块] 订单主表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderServiceImpl extends AbstractServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public IPage<OrderComplexVo> paging(OrderPagingRo ro) {
        return null;
    }

    @Override
    public OrderComplexVo getComplex(Long id) {
        return null;
    }


    @Override
    public void deleteOne(Long id) {

    }

    @Override
    public void shipped(Long id, ShippedOrderRo ro) {

    }

    @Override
    public void updateOne(Long id, UpdateOrderRo ro) {

    }

    @Override
    public void close(Long id, CloseOrderRo ro) {

    }
}
