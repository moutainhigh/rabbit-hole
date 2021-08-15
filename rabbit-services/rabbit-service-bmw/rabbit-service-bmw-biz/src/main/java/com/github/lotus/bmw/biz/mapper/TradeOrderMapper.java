package com.github.lotus.bmw.biz.mapper;

import com.github.lotus.bmw.biz.entity.TradeOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付模块] 交易单表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Mapper
public interface TradeOrderMapper extends BaseMapper<TradeOrder> {

}
