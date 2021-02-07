package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.pay.biz.entity.PayRecord;
import com.github.lotus.pay.biz.pojo.ro.PayRecordPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [支付网关] 支付记录表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Mapper
public interface PayRecordMapper extends BaseMapper<PayRecord> {

    IPage<PayRecord> paging(@Param("tradeId") Long tradeId, @Param("ro") PayRecordPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
