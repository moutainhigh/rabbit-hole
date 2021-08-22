package com.github.lotus.mall.biz.service.impl;

import com.github.lotus.mall.biz.entity.OrderDiscount;
import com.github.lotus.mall.biz.mapper.OrderDiscountMapper;
import com.github.lotus.mall.biz.service.OrderDiscountService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [订单模块] 订单优惠详项表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderDiscountServiceImpl extends AbstractServiceImpl<OrderDiscountMapper, OrderDiscount> implements OrderDiscountService {

}
