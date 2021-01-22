package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PaymentWayRuleItem;
import com.github.lotus.pay.biz.mapper.PaymentWayRuleItemMapper;
import com.github.lotus.pay.biz.service.PaymentWayRuleItemService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [支付网关] 支付渠道规则对应的支付渠道表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-07-18
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentWayRuleItemServiceImpl extends AbstractServiceImpl<PaymentWayRuleItemMapper, PaymentWayRuleItem> implements PaymentWayRuleItemService {

}
