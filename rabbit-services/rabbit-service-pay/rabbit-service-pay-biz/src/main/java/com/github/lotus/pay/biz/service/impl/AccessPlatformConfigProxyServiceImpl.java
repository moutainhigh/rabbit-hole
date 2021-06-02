package com.github.lotus.pay.biz.service.impl;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.github.lotus.common.datadict.pay.PaymentPlatform;
import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import com.github.lotus.pay.biz.mapstruct.PlatformAlipayConfigMapping;
import com.github.lotus.pay.biz.mapstruct.PlatformWxpayConfigMapping;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformSaveRo;
import com.github.lotus.pay.biz.service.AccessPlatformConfigProxyService;
import com.github.lotus.pay.biz.service.PlatformAlipayConfigService;
import com.github.lotus.pay.biz.service.PlatformWxpayConfigService;
import com.github.lotus.common.utils.Rules;
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
        return (Long) Rules.create()
            .rule(PaymentPlatform.AliPay, Rules.Supplier(() -> {
                AccessPlatformSaveRo.AliPayConfig aliPayConfig = ValidUtils.notNull(ro.getAliPayConfig());
                PlatformAlipayConfig entity = platformAlipayConfigMapping.asPlatformAlipayConfig(aliPayConfig);
                boolean isOk = platformAlipayConfigService.validInsert(entity);
                ValidUtils.isTrue(isOk);
                return entity.getId();
            }))
            .rule(PaymentPlatform.WxPay, Rules.Supplier(() -> {
                AccessPlatformSaveRo.WxPayConfig wxPayConfig = ValidUtils.notNull(ro.getWxPayConfig());
                PlatformWxpayConfig entity = platformWxpayConfigMapping.asPlatformWxpayConfig(wxPayConfig);
                boolean isOk = platformWxpayConfigService.validInsert(entity);
                ValidUtils.isTrue(isOk);
                return entity.getId();
            }))
            .of(ICode.ofThrow(ro.getPlatform(), PaymentPlatform.class)).orElse(null);
    }

    @Override
    public void updateOne(@NonNull Long refId, AccessPlatformSaveRo ro) {
        Rules.create()
            .rule(PaymentPlatform.AliPay, Rules.Runnable(() -> {
                AccessPlatformSaveRo.AliPayConfig aliPayConfig = ValidUtils.notNull(ro.getAliPayConfig());
                PlatformAlipayConfig entity = platformAlipayConfigMapping.asPlatformAlipayConfig(aliPayConfig);
                entity.setId(refId);
                boolean isOk = platformAlipayConfigService.validUpdateById(entity);
                ValidUtils.isTrue(isOk);
            }))
            .rule(PaymentPlatform.WxPay, Rules.Runnable(() -> {
                AccessPlatformSaveRo.WxPayConfig wxPayConfig = ValidUtils.notNull(ro.getWxPayConfig());
                PlatformWxpayConfig entity = platformWxpayConfigMapping.asPlatformWxpayConfig(wxPayConfig);
                entity.setId(refId);
                boolean isOk = platformWxpayConfigService.validUpdateById(entity);
                ValidUtils.isTrue(isOk);
            }))
            .of(ICode.ofThrow(ro.getPlatform(), PaymentPlatform.class));

    }

    @Override
    public void removeByRefTypeAndRefId(@NonNull String refType, @NonNull Long refId) {
        Rules.create()
            .rule(PaymentPlatform.AliPay, Rules.Runnable(() -> {
                platformAlipayConfigService.removeById(refId);
            }))
            .rule(PaymentPlatform.WxPay, Rules.Runnable(() -> {
                platformWxpayConfigService.removeById(refId);
            }))
            .of(ICode.ofThrow(refType, PaymentPlatform.class));
    }

    @Override
    public Model<?> getByRefTypeAndRefId(@NonNull String refType, @NonNull Long refId) {
        return (Model<?>) Rules.create()
            .rule(PaymentPlatform.AliPay, Rules.Supplier(() -> platformAlipayConfigService.getById(refId)))
            .rule(PaymentPlatform.WxPay, Rules.Supplier(() -> platformWxpayConfigService.getById(refId)))
            .of(ICode.ofThrow(refType, PaymentPlatform.class)).orElse(null);
    }
}
