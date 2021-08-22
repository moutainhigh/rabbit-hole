package com.github.lotus.mall.biz.mapper;

import com.github.lotus.mall.biz.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [订单模块] 订单主表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
