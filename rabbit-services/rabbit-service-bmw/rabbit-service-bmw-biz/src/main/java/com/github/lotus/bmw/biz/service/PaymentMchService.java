package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.biz.entity.PaymentMch;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付模块] 支付商户表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface PaymentMchService extends AbstractService<PaymentMch> {

    Optional<PaymentMch> getByAccessMchIdAndPaySceneIdAndPayType(Long accessMchId, Long paySceneId, String payType);

    Optional<PaymentMch> getByAccessMchIdAndSceneCodeAndPayType(Long accessMchId, String sceneCode, String payType);

    Optional<PaymentMch> getByEncoding(String paymentMchCode);
}
