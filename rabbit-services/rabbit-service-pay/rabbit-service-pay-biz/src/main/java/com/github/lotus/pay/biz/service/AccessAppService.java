package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.pojo.ro.AccessAppInsertRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 接入应用表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface AccessAppService extends AbstractService<AccessApp> {

    Optional<AccessApp> getByEncoding(String encoding);

    void sendAsyncNotifyApp(Long notifyAccessAppId);

    void insertOne(AccessAppInsertRo ro);
}
