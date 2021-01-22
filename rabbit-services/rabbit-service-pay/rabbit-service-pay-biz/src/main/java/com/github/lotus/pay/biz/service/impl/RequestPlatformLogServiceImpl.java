package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.RequestPlatformLog;
import com.github.lotus.pay.biz.mapper.RequestPlatformLogMapper;
import com.github.lotus.pay.biz.service.RequestPlatformLogService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [支付网关] 所有和第三方支付交易日志表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RequestPlatformLogServiceImpl extends AbstractServiceImpl<RequestPlatformLogMapper, RequestPlatformLog> implements RequestPlatformLogService {

}
