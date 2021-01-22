package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.pay.biz.entity.PaymentWayRule;
import com.github.lotus.pay.biz.entity.PaymentWayRuleItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [支付网关] 支付渠道规则表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-07-18
 */
@Mapper
public interface PaymentWayRuleMapper extends BaseMapper<PaymentWayRule> {

    List<PaymentWayRuleItem> selectListByAppIdAndSceneCode(@Param("appId") Long appId, @Param("sceneCode") String sceneCode);
}
