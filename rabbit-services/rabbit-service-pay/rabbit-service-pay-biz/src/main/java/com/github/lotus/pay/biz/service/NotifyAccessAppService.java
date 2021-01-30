package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.NotifyAccessApp;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付网关] 事件通知列表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface NotifyAccessAppService extends AbstractService<NotifyAccessApp> {

    Long createPayNotify(Long tradeId);

    Long createRefundNotify(Long refundRecordId);
}
