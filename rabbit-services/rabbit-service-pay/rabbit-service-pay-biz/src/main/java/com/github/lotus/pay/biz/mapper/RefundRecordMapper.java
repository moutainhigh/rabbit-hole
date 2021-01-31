package com.github.lotus.pay.biz.mapper;

import com.github.lotus.pay.biz.entity.RefundRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
