package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PaymentMchType;
import com.github.lotus.bmw.biz.mapper.PaymentMchTypeMapper;
import com.github.lotus.bmw.biz.service.PaymentMchTypeService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [支付模块] 支付商户的商户类型表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentMchTypeServiceImpl extends AbstractServiceImpl<PaymentMchTypeMapper, PaymentMchType> implements PaymentMchTypeService {

}
