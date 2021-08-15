package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PaymentMchAlipayConfig;
import com.github.lotus.bmw.biz.mapper.PaymentMchAlipayConfigMapper;
import com.github.lotus.bmw.biz.service.PaymentMchAlipayConfigService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [支付模块] 支付宝配置表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-15
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentMchAlipayConfigServiceImpl extends AbstractServiceImpl<PaymentMchAlipayConfigMapper, PaymentMchAlipayConfig> implements PaymentMchAlipayConfigService {

}
