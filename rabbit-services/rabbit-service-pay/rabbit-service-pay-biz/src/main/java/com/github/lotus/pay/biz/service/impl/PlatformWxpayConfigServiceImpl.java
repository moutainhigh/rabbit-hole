package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import com.github.lotus.pay.biz.mapper.PlatformWxpayConfigMapper;
import com.github.lotus.pay.biz.mapstruct.PlatformWxpayConfigMapping;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformSaveRo;
import com.github.lotus.pay.biz.service.PlatformWxpayConfigService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
    private final PlatformWxpayConfigMapping mapping;
    @Override
    public Long insert(AccessPlatformSaveRo.WxPayConfig config) {
        PlatformWxpayConfig entity = mapping.asPlatformWxpayConfig(config);
        validInsert(entity);
        return entity.getId();
    }
}
