package com.github.lotus.pay.biz.service.impl;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformInsertRo;
import com.github.lotus.pay.biz.service.AccessPlatformConfigProxyService;
import com.github.lotus.pay.biz.service.PlatformAlipayConfigService;
import com.github.lotus.pay.biz.service.PlatformWxpayConfigService;
import com.github.lotus.pay.biz.support.payment.helper.PaymentHelper;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public Long insertOne(AccessPlatformInsertRo ro) {
        PaymentPlatform paymentPlatform = ICode.ofThrow(ro.getPlatform(), PaymentPlatform.class);
        AccessPlatformInsertRo.AliPayConfig aliPayConfig = ro.getAliPayConfig();
        AccessPlatformInsertRo.WxPayConfig wxPayConfig = ro.getWxPayConfig();

        Long refId;
        switch (paymentPlatform) {
            case AliPay: {
                ValidUtils.notNull(aliPayConfig);
                refId = platformAlipayConfigService.insert(aliPayConfig);
                break;
            }
            case WxPay: {
                ValidUtils.notNull(wxPayConfig);
                refId = platformWxpayConfigService.insert(wxPayConfig);
                break;
            }
            case Unknown:
            default:
                throw new UnsupportedOperationException();
        }
        return refId;
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
        PaymentPlatform paymentPlatform = PaymentHelper.asPaymentPlatform(refType);
        Model<?> result;
        switch (Objects.requireNonNull(paymentPlatform)) {
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
