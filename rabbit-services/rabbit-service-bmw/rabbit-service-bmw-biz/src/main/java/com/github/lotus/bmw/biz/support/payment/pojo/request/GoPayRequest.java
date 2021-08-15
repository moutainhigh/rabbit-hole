package com.github.lotus.bmw.biz.support.payment.pojo.request;

import com.github.lotus.bmw.biz.docking.wxpay.WxPayHelper;
import com.github.lotus.bmw.biz.support.payment.ConfigStorageDto;
import com.github.lotus.common.datadict.bmw.PaymentMchPayType;
import com.github.lotus.common.datadict.pay.PayMode;
import com.github.lotus.bmw.biz.support.payment.pojo.response.GoPayResponse;
import com.github.lotus.common.utils.Rules;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.payment.PaymentResponse;
import in.hocg.payment.alipay.v2.request.AliPayRequest;
import in.hocg.payment.alipay.v2.request.TradeAppPayRequest;
import in.hocg.payment.alipay.v2.request.TradePreCreateRequest;
import in.hocg.payment.alipay.v2.request.TradeWapPayRequest;
import in.hocg.payment.alipay.v2.response.TradePreCreateResponse;
import in.hocg.payment.wxpay.v2.request.UnifiedOrderRequest;
import in.hocg.payment.wxpay.v2.request.WxPayRequest;
import in.hocg.payment.wxpay.v2.response.UnifiedOrderResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.function.Supplier;

import static com.github.lotus.common.datadict.bmw.PaymentMchPayType.*;

/**
 * Created by hocgin on 2020/5/31.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Builder
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class GoPayRequest extends AbsRequest {
    @ApiModelProperty
    protected final ConfigStorageDto configStorage;
    @NonNull
    @ApiModelProperty(value = "订单支付金额", required = true)
    private final BigDecimal payAmount;
    @NonNull
    @ApiModelProperty("交易单号(网关)")
    private final String tradeSn;
    @NonNull
    @ApiModelProperty("支付记录")
    private final Long payRecordId;


    @ApiModelProperty(value = "[可选] 微信用户(微信支付必须)")
    private final String wxOpenId;
    @ApiModelProperty(value = "[可选] (支付宝Wap支付)")
    private final String quitUrl;

    private String getSubject() {
        return String.format("订单: %s", this.tradeSn);
    }

    private AliPayRequest aliPayAppRequest() {
        TradeAppPayRequest request = new TradeAppPayRequest();
        request.setBizContent2(new TradeAppPayRequest.BizContent()
            .setSubject(this.getSubject())
            .setTotalAmount(String.valueOf(this.getPayAmount()))
            .setOutTradeNo(this.getTradeSn()));
        request.setNotifyUrl(this.getNotifyUrl());
        return request;
    }

    private AliPayRequest aliPayWapRequest() {
        TradeWapPayRequest request = new TradeWapPayRequest();
        request.setBizContent2(new TradeWapPayRequest.BizContent()
            .setSubject(this.getSubject())
            .setTotalAmount(String.valueOf(this.getPayAmount()))
            .setProductCode("QUICK_WAP_WAY")
            .setQuitUrl(getQuitUrl())
            .setOutTradeNo(this.getTradeSn()));
        request.setNotifyUrl(this.getNotifyUrl());
        return request;
    }

    private AliPayRequest aliPayPcRequest() {
        TradeWapPayRequest request = new TradeWapPayRequest();
        request.setBizContent2(new TradeWapPayRequest.BizContent()
            .setSubject(this.getSubject())
            .setTotalAmount(String.valueOf(this.getPayAmount()))
            .setProductCode("FAST_INSTANT_TRADE_PAY")
            .setOutTradeNo(this.getTradeSn()));
        request.setNotifyUrl(this.getNotifyUrl());
        return request;
    }

    private AliPayRequest aliPayQrCodeRequest() {
        TradePreCreateRequest request = new TradePreCreateRequest();
        request.setBizContent2(new TradePreCreateRequest.BizContent()
            .setSubject(this.getSubject())
            .setTotalAmount(String.valueOf(this.getPayAmount()))
            .setOutTradeNo(this.getTradeSn()));
        request.setNotifyUrl(this.getNotifyUrl());
        return request;
    }

    private WxPayRequest wxPayJSAPIRequest() {
        final String wxOpenId = this.getWxOpenId();
        ValidUtils.notNull(wxOpenId, "微信支付需要指定用户ID");

        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setOpenId(wxOpenId);
        request.setTradeType("JSAPI");
        request.setBody(this.getSubject());
        request.setNotifyUrl(this.getNotifyUrl());
        request.setOutTradeNo(this.getTradeSn());
        request.setTotalFee(WxPayHelper.toWxPayAmount(payAmount));
        request.setSpbillCreateIp(this.getClientIp());
        return request;
    }

    private WxPayRequest wxPayAPPRequest() {
        final String wxOpenId = this.getWxOpenId();
        ValidUtils.notNull(wxOpenId, "微信支付需要指定用户ID");

        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setTradeType("APP");
        request.setBody(this.getSubject());
        request.setNotifyUrl(this.getNotifyUrl());
        request.setOutTradeNo(this.getTradeSn());
        request.setTotalFee(WxPayHelper.toWxPayAmount(payAmount));
        request.setSpbillCreateIp(this.getClientIp());
        return request;
    }

    private WxPayRequest wxPayNativeRequest() {
        final String wxOpenId = this.getWxOpenId();
        ValidUtils.notNull(wxOpenId, "微信支付需要指定用户ID");

        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setTradeType("APP");
        request.setBody(this.getSubject());
        request.setNotifyUrl(this.getNotifyUrl());
        request.setOutTradeNo(this.getTradeSn());
        request.setTotalFee(WxPayHelper.toWxPayAmount(payAmount));
        request.setSpbillCreateIp(this.getClientIp());
        return request;
    }

    public GoPayResponse request(String payType) {
        final GoPayResponse result = new GoPayResponse().setPayType(payType);
        Rules.create()
            .rule(AliPay_App, Rules.Runnable(() -> {
                final PaymentResponse response = this.request(this.aliPayAppRequest());
                result.setApp(response.getContent());
            }))
            .rule(AliPay_QrCode, Rules.Runnable(() -> {
                final PaymentResponse response = this.request(this.aliPayQrCodeRequest());
                result.setApp(response.getContent());
            }))
            .rule(AliPay_PC, Rules.Runnable(() -> {
                final PaymentResponse response = this.request(this.aliPayPcRequest());
                result.setMethod("POST");
                result.setForm(response.getContent());
            }))
            .rule(AliPay_Wap, Rules.Runnable(() -> {
                final PaymentResponse response = this.request(this.aliPayWapRequest());
                result.setMethod("POST");
                result.setForm(response.getContent());
            }))
            .rule(WxPay_App, Rules.Runnable(() -> {
                final UnifiedOrderResponse response = this.request(this.wxPayAPPRequest());
                result.setForm(response.getContent());
            }))
            .rule(WxPay_JSAPI, Rules.Runnable(() -> {
                WxPayRequest request = this.wxPayJSAPIRequest();
                final UnifiedOrderResponse response = this.request(request);
                result.setWxJSApi(GoPayResponse.WxJSAPI.NEW("", response.getNonceStr(), response.getPrepayId(), request.getSignType(), response.getSign()));
                result.setForm(response.getContent());
            }))
            .rule(WxPay_Native, Rules.Runnable(() -> {
                final WxPayRequest request = this.wxPayNativeRequest();
                final UnifiedOrderResponse response = this.request(request);
                result.setForm(response.getContent());
            }))
            .of(ICode.ofThrow(payType, PaymentMchPayType.class));
        return result;
    }

    private String getNotifyUrl() {
        return getPaymentNotifyUrl(this.payRecordId);
    }

}
