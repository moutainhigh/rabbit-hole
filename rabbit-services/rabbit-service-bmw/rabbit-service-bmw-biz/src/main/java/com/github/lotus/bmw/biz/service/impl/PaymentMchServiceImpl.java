package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PaymentMch;
import com.github.lotus.bmw.biz.mapper.PaymentMchMapper;
import com.github.lotus.bmw.biz.service.PaymentMchService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * <p>
 * [支付模块] 支付商户表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentMchServiceImpl extends AbstractServiceImpl<PaymentMchMapper, PaymentMch>
    implements PaymentMchService {

    @Override
    public Optional<PaymentMch> getByAccessMchIdAndSceneCodeAndPayType(Long accessMchId, String sceneCode, String payType) {
        return baseMapper.getByAccessMchIdAndSceneCodeAndPayType(accessMchId, sceneCode, payType);
    }

    @Override
    public Optional<PaymentMch> getByEncoding(String paymentMchCode) {
        return this.lambdaQuery().eq(PaymentMch::getEncoding, paymentMchCode).oneOpt();
    }
}
