package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.PaymentMode;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 第三方支付平台对应的支付方式表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface PaymentModeService extends AbstractService<PaymentMode> {

    Optional<PaymentMode> getByEncoding(String payMode);
}
