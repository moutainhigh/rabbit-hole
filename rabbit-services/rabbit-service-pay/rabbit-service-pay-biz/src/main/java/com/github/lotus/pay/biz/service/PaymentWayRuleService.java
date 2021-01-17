package com.github.lotus.pay.biz.service;


import com.github.lotus.pay.api.pojo.vo.PaymentWayVo;
import com.github.lotus.pay.biz.entity.PaymentWayRule;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [支付网关] 支付渠道规则表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-07-18
 */
public interface PaymentWayRuleService extends AbstractService<PaymentWayRule> {

    List<PaymentWayVo> queryPaymentWay(Long appId, String sceneCode);
}
