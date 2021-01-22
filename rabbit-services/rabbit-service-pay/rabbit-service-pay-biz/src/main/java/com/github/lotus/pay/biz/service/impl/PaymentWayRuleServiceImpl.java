package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.api.pojo.vo.PaymentWayVo;
import com.github.lotus.pay.biz.entity.PaymentWayRule;
import com.github.lotus.pay.biz.entity.PaymentWayRuleItem;
import com.github.lotus.pay.biz.mapper.PaymentWayRuleMapper;
import com.github.lotus.pay.biz.mapstruct.PaymentWayRuleItemMapping;
import com.github.lotus.pay.biz.service.PaymentWayRuleService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * [支付网关] 支付渠道规则表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-07-18
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentWayRuleServiceImpl extends AbstractServiceImpl<PaymentWayRuleMapper, PaymentWayRule>
    implements PaymentWayRuleService {
    private final PaymentWayRuleItemMapping mapping;

    @Override
    @Transactional
    public List<PaymentWayVo> queryPaymentWay(Long appId, String sceneCode) {
        final List<PaymentWayRuleItem> result = baseMapper.selectListByAppIdAndSceneCode(appId, sceneCode);
        return mapping.asPaymentWayVo(result);
    }
}
