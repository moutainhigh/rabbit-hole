package com.github.lotus.bmw.biz.support.payment.pojo.request;

import com.github.lotus.bmw.biz.docking.wxpay.WxPayHelper;
import com.github.lotus.bmw.biz.support.payment.ConfigStorageDto;
import com.github.lotus.common.datadict.bmw.PaymentMchPayType;
import com.github.lotus.common.datadict.pay.PayMode;
import com.github.lotus.bmw.biz.support.payment.pojo.response.GoPayResponse;
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
    @ApiModelProperty(value = "支付方式", required = true)
    private final String payMode;
    @NonNull
    @ApiModelProperty(value = "订单支付金额", required = true)
    private final BigDecimal payAmount;
    @NonNull
    @ApiModelProperty("交易单号(网关)")
    private final String tradeSn;
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
        switch (ICode.ofThrow(payType, PaymentMchPayType.class)) {
            case AliPay_App: {
                final AliPayRequest request = this.aliPayAppRequest();
                final PaymentResponse response = this.request(request);
                result.setApp(response.getContent());
                break;
            }
            case AliPay_QrCode: {
                final AliPayRequest request = this.aliPayQrCodeRequest();
                final TradePreCreateResponse response = this.request(request);
                result.setQrCode(response.getQrCode());
                break;
            }
            case AliPay_PC: {
                final AliPayRequest request = this.aliPayPcRequest();
                final PaymentResponse response = this.request(request);
                result.setMethod("POST");
                result.setForm(response.getContent());
                break;
            }
            case AliPay_Wap: {
                final AliPayRequest request = this.aliPayWapRequest();
                final PaymentResponse response = this.request(request);
                result.setMethod("POST");
                result.setForm(response.getContent());
                break;
            }
            case WxPay_App: {
                final WxPayRequest request = this.wxPayAPPRequest();
                final UnifiedOrderResponse response = this.request(request);
                result.setApp(response.getContent());
                break;
            }
            case WxPay_JSAPI: {
                final WxPayRequest request = this.wxPayJSAPIRequest();
                final UnifiedOrderResponse response = this.request(request);
                result.setWxJSApi(GoPayResponse.WxJSAPI.NEW("", response.getNonceStr(), response.getPrepayId(), request.getSignType(), response.getSign()));
                break;
            }
            case WxPay_Native: {
                final WxPayRequest request = this.wxPayNativeRequest();
                final UnifiedOrderResponse response = this.request(request);
                result.setWxNative(response.getContent());
                break;
            }
            default: {
                throw ServiceException.wrap("暂不支持该支付方式");
            }
        }
        return result;
    }

    private String getNotifyUrl() {
        return getPaymentNotifyUrl();
    }

}
