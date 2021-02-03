package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.common.datadict.pay.Feature;
import com.github.lotus.common.datadict.pay.LogType;
import com.github.lotus.pay.biz.entity.PlatformNotifyLog;
import com.github.lotus.pay.biz.mapper.PlatformNotifyLogMapper;
import com.github.lotus.pay.biz.service.PlatformNotifyLogService;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.enums.ICode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * [支付网关] 所有第三方支付通知日志表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-31
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlatformNotifyLogServiceImpl extends AbstractServiceImpl<PlatformNotifyLogMapper, PlatformNotifyLog> implements PlatformNotifyLogService {

    @Override
    public Long log(MessageContext context, String data) {
        Long accessPlatformId = context.getAccessPlatformId();
        Feature feature = ICode.ofThrow(context.getFeature(), Feature.class);
        LocalDateTime now = LocalDateTime.now();

        PlatformNotifyLog entity = new PlatformNotifyLog();
        entity.setRequestBody(data);
        entity.setAccessPlatformId(accessPlatformId);
        entity.setFeature(feature.getCodeStr());
        entity.setLogType(LogType.AsyncNotify.getCodeStr());
        entity.setCreatedIp(context.getClientIp());
        entity.setCreatedAt(now);
        this.validInsert(entity);
        return entity.getId();
    }

    @Async
    @Override
    public void updateLog(Long id, String returnStr) {
        PlatformNotifyLog entity = new PlatformNotifyLog();
        entity.setId(id);
        entity.setReturnBody(returnStr);
        validUpdateById(entity);
    }

}
