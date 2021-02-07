package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.pay.biz.entity.RefundRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.pay.biz.pojo.ro.RefundRecordPagingRo;
import com.github.lotus.pay.biz.pojo.vo.RefundRecordOrdinaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [支付网关] 退款记录表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Mapper
public interface RefundRecordMapper extends BaseMapper<RefundRecord> {

    IPage<RefundRecord> pagingByTradeId(@Param("tradeId") Long tradeId, @Param("ro") RefundRecordPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
