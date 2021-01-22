package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PaymentApp;
import com.github.lotus.pay.biz.mapper.PaymentAppMapper;
import com.github.lotus.pay.biz.service.PaymentAppService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 接入方表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentAppServiceImpl extends AbstractServiceImpl<PaymentAppMapper, PaymentApp> implements PaymentAppService {

    @Override
    public Optional<PaymentApp> selectOneByAppSn(Long appSn) {
        return lambdaQuery().eq(PaymentApp::getAppSn, appSn).oneOpt();
    }
}
