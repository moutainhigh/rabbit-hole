package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PaymentMode;
import com.github.lotus.pay.biz.mapper.PaymentModeMapper;
import com.github.lotus.pay.biz.service.PaymentModeService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 第三方支付平台对应的支付方式表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentModeServiceImpl extends AbstractServiceImpl<PaymentModeMapper, PaymentMode> implements PaymentModeService {

    @Override
    public Optional<PaymentMode> getByEncoding(String payMode) {
        return lambdaQuery().eq(PaymentMode::getEncoding, payMode).oneOpt();
    }
}
