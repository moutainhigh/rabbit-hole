package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.github.lotus.pay.biz.mapper.PlatformAlipayConfigMapper;
import com.github.lotus.pay.biz.mapstruct.PlatformAlipayConfigMapping;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformInsertRo;
import com.github.lotus.pay.biz.service.PlatformAlipayConfigService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [支付网关] 支付宝配置表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlatformAlipayConfigServiceImpl extends AbstractServiceImpl<PlatformAlipayConfigMapper, PlatformAlipayConfig>
    implements PlatformAlipayConfigService {
    private final PlatformAlipayConfigMapping mapping;

    @Override
    public Long insert(AccessPlatformInsertRo.AliPayConfig config) {
        PlatformAlipayConfig entity = mapping.asPlatformAlipayConfig(config);
        validInsert(entity);
        return entity.getId();
    }
}
