package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.PaymentPlatform;
import com.github.lotus.pay.biz.enumns.PaymentWayType;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;


import java.util.Optional;

/**
 * <p>
 * [支付网关] 支付平台表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
public interface PaymentPlatformService extends AbstractService<PaymentPlatform> {

    Optional<PaymentPlatform> selectOneByTradeIdAndPaymentWayAndStatus(Long tradeId, PaymentWayType paymentWay, Boolean enabled);

    Optional<PaymentPlatform> selectOneByPlatformAppidAndPlatformType(String platformAppid, Integer platformType);
}
