package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.AccessPlatform;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformInsertRo;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 接入平台表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface AccessPlatformService extends AbstractService<AccessPlatform> {

    Optional<AccessPlatform> getByAppidAndRefType(String accessAppSn, String refType);

    ConfigStorageDto getConfigStorage(Long accessPlatformId);

    Optional<AccessPlatform> getByAccessAppIdAndRefType(Long accessAppId, String refType);

    Optional<AccessPlatform> getByAccessAppIdAndPayMode(Long accessAppId, String payMode);

    void insertOne(AccessPlatformInsertRo ro);
}
