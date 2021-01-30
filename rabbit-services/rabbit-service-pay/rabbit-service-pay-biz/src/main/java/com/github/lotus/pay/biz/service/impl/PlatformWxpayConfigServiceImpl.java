package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import com.github.lotus.pay.biz.mapper.PlatformWxpayConfigMapper;
import com.github.lotus.pay.biz.service.PlatformWxpayConfigService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [支付网关] 微信支付配置表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlatformWxpayConfigServiceImpl extends AbstractServiceImpl<PlatformWxpayConfigMapper, PlatformWxpayConfig> implements PlatformWxpayConfigService {

}
