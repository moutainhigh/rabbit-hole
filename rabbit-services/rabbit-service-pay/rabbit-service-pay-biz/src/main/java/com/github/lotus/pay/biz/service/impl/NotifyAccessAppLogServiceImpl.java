package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.NotifyAccessAppLog;
import com.github.lotus.pay.biz.mapper.NotifyAccessAppLogMapper;
import com.github.lotus.pay.biz.service.NotifyAccessAppLogService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [支付网关] 所有通知应用方日志表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NotifyAccessAppLogServiceImpl extends AbstractServiceImpl<NotifyAccessAppLogMapper, NotifyAccessAppLog> implements NotifyAccessAppLogService {

    @Override
    public List<NotifyAccessAppLog> listByNotifyAccessAppId(Long notifyAccessAppId) {
        return lambdaQuery().eq(NotifyAccessAppLog::getNotifyAccessAppId, notifyAccessAppId).list();
    }
}
