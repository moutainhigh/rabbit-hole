package com.github.lotus.pay.biz.service;


import com.github.lotus.pay.biz.entity.NotifyApp;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付网关] 事件通知列表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
public interface NotifyAppService extends AbstractService<NotifyApp> {

    Long savePaymentNotify(Long tradeId);

    Long saveRefundNotify(Long refundId);
}
