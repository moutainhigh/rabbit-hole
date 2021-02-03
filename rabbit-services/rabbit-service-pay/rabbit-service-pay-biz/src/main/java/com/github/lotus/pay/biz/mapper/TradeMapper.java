package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.pay.biz.entity.Trade;
import com.github.lotus.pay.biz.pojo.ro.TradeCompleteRo;
import com.github.lotus.pay.biz.pojo.ro.TradePagingRo;
import com.github.lotus.pay.biz.pojo.vo.TradeComplexVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [支付网关] 交易账单表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Mapper
public interface TradeMapper extends BaseMapper<Trade> {

    IPage<Trade> complete(@Param("ro") TradeCompleteRo ro, @Param("ofPage") Page ofPage);

    IPage<Trade> paging(@Param("ro") TradePagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
