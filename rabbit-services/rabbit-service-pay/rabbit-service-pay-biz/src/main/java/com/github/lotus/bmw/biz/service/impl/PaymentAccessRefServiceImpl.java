package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PaymentAccessRef;
import com.github.lotus.bmw.biz.mapper.PaymentAccessRefMapper;
import com.github.lotus.bmw.biz.service.PaymentAccessRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [支付模块] 支付商户 x 接入商户表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentAccessRefServiceImpl extends AbstractServiceImpl<PaymentAccessRefMapper, PaymentAccessRef> implements PaymentAccessRefService {

}
