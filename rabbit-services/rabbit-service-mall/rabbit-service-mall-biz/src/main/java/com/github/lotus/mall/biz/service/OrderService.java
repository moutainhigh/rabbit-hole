package com.github.lotus.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.Order;
import com.github.lotus.mall.biz.pojo.ro.CloseOrderRo;
import com.github.lotus.mall.biz.pojo.ro.OrderPagingRo;
import com.github.lotus.mall.biz.pojo.ro.ShippedOrderRo;
import com.github.lotus.mall.biz.pojo.ro.UpdateOrderRo;
import com.github.lotus.mall.biz.pojo.vo.OrderComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [订单模块] 订单主表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface OrderService extends AbstractService<Order> {

    IPage<OrderComplexVo> paging(OrderPagingRo ro);

    OrderComplexVo getComplex(Long id);


    void deleteOne(Long id);

    void shipped(Long id, ShippedOrderRo ro);

    void updateOne(Long id, UpdateOrderRo ro);

    void close(Long id, CloseOrderRo ro);
}
