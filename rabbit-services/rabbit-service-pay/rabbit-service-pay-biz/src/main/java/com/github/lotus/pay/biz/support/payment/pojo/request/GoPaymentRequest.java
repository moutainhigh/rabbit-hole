package com.github.lotus.pay.biz.support.payment.pojo.request;

import com.github.lotus.common.datadict.bmw.PayMode;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import com.github.lotus.pay.biz.support.payment.pojo.response.GoPaymentResponse;
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
public class GoPaymentRequest extends AbsRequest {
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
        request.setTotalFee(String.valueOf(this.getPayAmount().multiply(BigDecimal.valueOf(100L))));
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
        request.setTotalFee(String.valueOf(this.getPayAmount().multiply(BigDecimal.valueOf(100L))));
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
        request.setTotalFee(String.valueOf(this.getPayAmount().multiply(BigDecimal.valueOf(100L))));
        request.setSpbillCreateIp(this.getClientIp());
        return request;
    }

    public GoPaymentResponse request() {
        final GoPaymentResponse result = new GoPaymentResponse()
            .setPlatformType(getPlatform().getCode())
            .setPayMode(getPayMode());

        switch (ICode.ofThrow(getPayMode(), PayMode.class)) {
            case AliPayWithApp: {
                final AliPayRequest request = this.aliPayAppRequest();
                final PaymentResponse response = this.request(request);
                result.setApp(response.getContent());
                break;
            }
            case AliPayWithQrCode: {
                final AliPayRequest request = this.aliPayQrCodeRequest();
                final TradePreCreateResponse response = this.request(request);
                result.setQrCode(response.getQrCode());
                break;
            }
            case AliPayWithPC: {
                final AliPayRequest request = this.aliPayPcRequest();
                final PaymentResponse response = this.request(request);
                result.setMethod("POST");
                result.setForm(response.getContent());
                break;
            }
            case AliPayWithWap: {
                final AliPayRequest request = this.aliPayWapRequest();
                final PaymentResponse response = this.request(request);
                result.setMethod("POST");
                result.setForm(response.getContent());
                break;
            }
            case WxPayWithApp: {
                final WxPayRequest request = this.wxPayAPPRequest();
                final UnifiedOrderResponse response = this.request(request);
                result.setApp(response.getContent());
                break;
            }
            case WxPayWithJSAPI: {
                final WxPayRequest request = this.wxPayJSAPIRequest();
                final UnifiedOrderResponse response = this.request(request);
                result.setWxJSApi(GoPaymentResponse.WxJSAPI.NEW("", response.getNonceStr(), response.getPrepayId(), request.getSignType(), response.getSign()));
                break;
            }
            case WxPayWithNative: {
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
