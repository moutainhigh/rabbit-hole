package com.github.lotus.pay.biz.service;


import com.github.lotus.pay.biz.entity.PaymentApp;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 接入方表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
public interface PaymentAppService extends AbstractService<PaymentApp> {

    Optional<PaymentApp> getByAppid(String appid);
}
