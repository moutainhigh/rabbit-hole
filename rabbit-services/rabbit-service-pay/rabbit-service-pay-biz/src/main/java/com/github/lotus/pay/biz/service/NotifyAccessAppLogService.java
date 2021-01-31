package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.NotifyAccessAppLog;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [支付网关] 所有通知应用方日志表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface NotifyAccessAppLogService extends AbstractService<NotifyAccessAppLog> {

    List<NotifyAccessAppLog> listByNotifyAccessAppId(Long notifyAccessAppId);

}
