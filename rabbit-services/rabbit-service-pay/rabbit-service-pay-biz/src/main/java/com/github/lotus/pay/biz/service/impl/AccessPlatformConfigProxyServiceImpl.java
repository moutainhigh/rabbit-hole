package com.github.lotus.pay.biz.service.impl;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import com.github.lotus.pay.biz.mapstruct.PlatformAlipayConfigMapping;
import com.github.lotus.pay.biz.mapstruct.PlatformWxpayConfigMapping;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformSaveRo;
import com.github.lotus.pay.biz.service.AccessPlatformConfigProxyService;
import com.github.lotus.pay.biz.service.PlatformAlipayConfigService;
import com.github.lotus.pay.biz.service.PlatformWxpayConfigService;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2021/2/2
 * email: hocgin@gmail.com
 * 授权应用配置代理, 目前支持
 * - 支付宝配置
 * - 微信支付配置
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccessPlatformConfigProxyServiceImpl implements AccessPlatformConfigProxyService {
    private final PlatformAlipayConfigService platformAlipayConfigService;
    private final PlatformWxpayConfigService platformWxpayConfigService;
    private final PlatformAlipayConfigMapping platformAlipayConfigMapping;
    private final PlatformWxpayConfigMapping platformWxpayConfigMapping;

    @Override
    public Long insertOne(AccessPlatformSaveRo ro) {
        PaymentPlatform paymentPlatform = ICode.ofThrow(ro.getPlatform(), PaymentPlatform.class);
        AccessPlatformSaveRo.AliPayConfig aliPayConfig = ro.getAliPayConfig();
        AccessPlatformSaveRo.WxPayConfig wxPayConfig = ro.getWxPayConfig();

        Long refId;
        switch (paymentPlatform) {
            case AliPay: {
                ValidUtils.notNull(aliPayConfig);
                PlatformAlipayConfig entity = platformAlipayConfigMapping.asPlatformAlipayConfig(aliPayConfig);
                boolean isOk = platformAlipayConfigService.validInsert(entity);
                ValidUtils.isTrue(isOk);
                refId = entity.getId();
                break;
            }
            case WxPay: {
                ValidUtils.notNull(wxPayConfig);
                PlatformWxpayConfig entity = platformWxpayConfigMapping.asPlatformWxpayConfig(wxPayConfig);
                boolean isOk = platformWxpayConfigService.validInsert(entity);
                ValidUtils.isTrue(isOk);
                refId = entity.getId();
                break;
            }
            case Unknown:
            default:
                throw new UnsupportedOperationException();
        }
        return refId;
    }

    @Override
    public void updateOne(@NonNull Long refId, AccessPlatformSaveRo ro) {
        String refType = ro.getPlatform();
        PaymentPlatform paymentPlatform = ICode.ofThrow(refType, PaymentPlatform.class);
        AccessPlatformSaveRo.AliPayConfig aliPayConfig = ro.getAliPayConfig();
        AccessPlatformSaveRo.WxPayConfig wxPayConfig = ro.getWxPayConfig();

        switch (paymentPlatform) {
            case AliPay: {
                ValidUtils.notNull(aliPayConfig);
                PlatformAlipayConfig entity = platformAlipayConfigMapping.asPlatformAlipayConfig(aliPayConfig);
                entity.setId(refId);
                boolean isOk = platformAlipayConfigService.validUpdateById(entity);
                ValidUtils.isTrue(isOk);
                break;
            }
            case WxPay: {
                ValidUtils.notNull(wxPayConfig);
                PlatformWxpayConfig entity = platformWxpayConfigMapping.asPlatformWxpayConfig(wxPayConfig);
                entity.setId(refId);
                boolean isOk = platformWxpayConfigService.validUpdateById(entity);
                ValidUtils.isTrue(isOk);
                break;
            }
            case Unknown:
            default:
                throw new UnsupportedOperationException();
        }

    }

    @Override
    public void removeByRefTypeAndRefId(@NonNull String refType, @NonNull Long refId) {
        PaymentPlatform paymentPlatform = ICode.ofThrow(refType, PaymentPlatform.class);
        switch (paymentPlatform) {
            case AliPay: {
                platformAlipayConfigService.removeById(refId);
                break;
            }
            case WxPay: {
                platformWxpayConfigService.removeById(refId);
                break;
            }
            case Unknown:
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public Model<?> getByRefTypeAndRefId(@NonNull String refType, @NonNull Long refId) {
        PaymentPlatform paymentPlatform = ICode.ofThrow(refType, PaymentPlatform.class);
        Model<?> result;
        switch (paymentPlatform) {
            case AliPay: {
                result = platformAlipayConfigService.getById(refId);
                break;
            }
            case WxPay: {
                result = platformWxpayConfigService.getById(refId);
                break;
            }
            case Unknown:
            default:
                throw new UnsupportedOperationException();
        }
        return result;
    }
}
