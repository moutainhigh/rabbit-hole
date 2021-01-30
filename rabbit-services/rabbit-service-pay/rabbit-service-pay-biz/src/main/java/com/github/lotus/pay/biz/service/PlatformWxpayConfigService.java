package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformInsertRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付网关] 微信支付配置表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface PlatformWxpayConfigService extends AbstractService<PlatformWxpayConfig> {

    Long insert(AccessPlatformInsertRo.WxPayConfig config);

}
