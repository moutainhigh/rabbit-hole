package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformSaveRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付网关] 支付宝配置表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface PlatformAlipayConfigService extends AbstractService<PlatformAlipayConfig> {

    Long insert(AccessPlatformSaveRo.AliPayConfig aliPayConfig);
}
