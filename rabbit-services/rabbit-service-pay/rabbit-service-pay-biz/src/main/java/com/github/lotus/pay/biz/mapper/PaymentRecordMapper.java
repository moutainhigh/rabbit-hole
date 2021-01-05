package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.pay.biz.entity.PaymentRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付网关] 支付记录表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Mapper
public interface PaymentRecordMapper extends BaseMapper<PaymentRecord> {

}
