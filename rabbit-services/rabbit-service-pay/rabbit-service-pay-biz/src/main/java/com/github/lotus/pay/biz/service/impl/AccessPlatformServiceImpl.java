package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.entity.AccessPlatform;
import com.github.lotus.pay.biz.mapper.AccessPlatformMapper;
import com.github.lotus.pay.biz.mapstruct.AccessPlatformMapping;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformSaveRo;
import com.github.lotus.pay.biz.service.AccessAppService;
import com.github.lotus.pay.biz.service.AccessPlatformConfigProxyService;
import com.github.lotus.pay.biz.service.AccessPlatformService;
import com.github.lotus.pay.biz.support.payment.helper.ConfigStorageHelper;
import com.github.lotus.pay.biz.support.payment.helper.PaymentHelper;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.payment.ConfigStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [支付网关] 接入平台表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccessPlatformServiceImpl extends AbstractServiceImpl<AccessPlatformMapper, AccessPlatform>
    implements AccessPlatformService {
    private final AccessAppService accessAppService;
    private final AccessPlatformMapping mapping;
    private final AccessPlatformConfigProxyService platformConfigProxyService;

    @Override
    public Optional<AccessPlatform> getByAppidAndRefType(String accessAppSn, String refType) {
        AccessApp accessApp = accessAppService.getByEncoding(accessAppSn).orElseThrow(() -> ServiceException.wrap("未找到接入应用"));
        Long accessAppId = accessApp.getId();
        return getByAccessAppIdAndRefType(accessAppId, refType);
    }

    @Override
    public ConfigStorageDto getConfigStorage(Long accessPlatformId) {
        AccessPlatform accessPlatform = getById(accessPlatformId);
        ValidUtils.notNull(accessPlatform);
        Long accessAppId = accessPlatform.getAccessAppId();
        AccessApp accessApp = accessAppService.getById(accessAppId);
        ValidUtils.notNull(accessApp);
        String appid = accessApp.getEncoding();

        String refType = accessPlatform.getRefType();
        Long refId = accessPlatform.getRefId();
        PaymentPlatform paymentPlatform = PaymentHelper.asPaymentPlatform(refType);

        ConfigStorage configStorage = ConfigStorageHelper.createConfigStorage(platformConfigProxyService.getByRefTypeAndRefId(refType, refId));
        return new ConfigStorageDto(appid, accessPlatformId, paymentPlatform, configStorage);
    }

    @Override
    public Optional<AccessPlatform> getByAccessAppIdAndRefType(Long accessAppId, String refType) {
        return lambdaQuery().eq(AccessPlatform::getAccessAppId, accessAppId).eq(AccessPlatform::getRefType, refType).oneOpt();
    }

    @Override
    public Optional<AccessPlatform> getByAccessAppIdAndPayMode(Long accessAppId, String payMode) {
        return baseMapper.getByAccessAppIdAndPayMode(accessAppId, payMode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOne(AccessPlatformSaveRo ro) {
        Long accessAppId = ro.getAccessAppId();
        String platform = ro.getPlatform();
        String clientIp = ro.getClientIp();
        Optional<AccessPlatform> accessPlatformOpt = getByAccessAppIdAndRefType(accessAppId, platform);

        AccessPlatform entity;
        if (accessPlatformOpt.isPresent()) {
            AccessPlatform accessPlatform = accessPlatformOpt.get();
            Long id = accessPlatform.getId();
            Long refId = accessPlatform.getRefId();
            platformConfigProxyService.updateOne(refId, ro);
            entity = mapping.asAccessPlatform(ro);
            entity.setId(id);
        } else {
            Long refId = platformConfigProxyService.insertOne(ro);
            entity = mapping.asAccessPlatform(ro);
            entity.setRefType(platform);
            entity.setRefId(refId);
            entity.setCreatedIp(clientIp);
        }
        validInsertOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByAccessAppId(Long accessAppId) {
        List<AccessPlatform> result = listByAccessAppId(accessAppId);
        for (AccessPlatform item : result) {
            platformConfigProxyService.removeByRefTypeAndRefId(item.getRefType(), item.getRefId());
        }
    }

    private List<AccessPlatform> listByAccessAppId(Long accessAppId) {
        return lambdaQuery().eq(AccessPlatform::getAccessAppId, accessAppId).list();
    }
}
