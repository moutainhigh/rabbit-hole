package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.pay.biz.entity.PaymentWayRuleItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付网关] 支付渠道规则对应的支付渠道表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-07-18
 */
@Mapper
public interface PaymentWayRuleItemMapper extends BaseMapper<PaymentWayRuleItem> {

}
