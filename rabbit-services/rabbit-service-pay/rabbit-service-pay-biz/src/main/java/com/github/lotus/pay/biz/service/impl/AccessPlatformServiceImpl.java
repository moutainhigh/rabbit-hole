package com.github.lotus.pay.biz.service.impl;

import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.entity.AccessPlatform;
import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import com.github.lotus.pay.biz.mapper.AccessPlatformMapper;
import com.github.lotus.pay.biz.service.AccessAppService;
import com.github.lotus.pay.biz.service.AccessPlatformService;
import com.github.lotus.pay.biz.service.PlatformAlipayConfigService;
import com.github.lotus.pay.biz.service.PlatformWxpayConfigService;
import com.github.lotus.pay.biz.support.payment.PaymentHelper;
import com.github.lotus.pay.biz.support.payment.helper.ConfigStorageHelper;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.payment.ConfigStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
    private final PlatformAlipayConfigService platformAlipayConfigService;
    private final PlatformWxpayConfigService platformWxpayConfigService;

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
        switch (Objects.requireNonNull(paymentPlatform)) {
            case AliPay:{
                PlatformAlipayConfig config = platformAlipayConfigService.getById(refId);
                ConfigStorage configStorage = ConfigStorageHelper.createAliPayConfigStorage(config);
                return new ConfigStorageDto(appid, PaymentPlatform.AliPay, configStorage);
            }
            case WxPay:{
                PlatformWxpayConfig config = platformWxpayConfigService.getById(refId);
                ConfigStorage configStorage = ConfigStorageHelper.createWxPayConfigStorage(config);
                return new ConfigStorageDto(appid, PaymentPlatform.WxPay, configStorage);
            }
            case Unknown:
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public Optional<AccessPlatform> getByAccessAppIdAndRefType(Long accessAppId, String refType) {
        return lambdaQuery().eq(AccessPlatform::getAccessAppId, accessAppId).eq(AccessPlatform::getRefType, refType).oneOpt();
    }
}
