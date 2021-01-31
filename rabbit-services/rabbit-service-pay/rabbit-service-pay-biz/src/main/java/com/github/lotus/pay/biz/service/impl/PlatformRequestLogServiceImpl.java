package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PlatformRequestLog;
import com.github.lotus.pay.biz.mapper.PlatformRequestLogMapper;
import com.github.lotus.pay.biz.service.PlatformRequestLogService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [支付网关] 所有和第三方支付交易日志表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlatformRequestLogServiceImpl extends AbstractServiceImpl<PlatformRequestLogMapper, PlatformRequestLog>
    implements PlatformRequestLogService {

}
