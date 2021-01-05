package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.NotifyAppLog;
import com.github.lotus.pay.biz.mapper.NotifyAppLogMapper;
import com.github.lotus.pay.biz.service.NotifyAppLogService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * [支付网关] 所有通知应用方日志表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NotifyAppLogServiceImpl extends AbstractServiceImpl<NotifyAppLogMapper, NotifyAppLog> implements NotifyAppLogService {

    @Override
    public List<NotifyAppLog> selectListByNotifyAppId(Long notifyId) {
        return lambdaQuery().eq(NotifyAppLog::getNotifyAppId, notifyId).list();
    }
}
