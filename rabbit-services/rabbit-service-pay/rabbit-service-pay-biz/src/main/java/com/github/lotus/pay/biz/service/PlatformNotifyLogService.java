package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.PlatformNotifyLog;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付网关] 所有第三方支付通知日志表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-31
 */
public interface PlatformNotifyLogService extends AbstractService<PlatformNotifyLog> {

    Long log(MessageContext context, String data);

    void updateLog(Long id, String returnStr);
}
