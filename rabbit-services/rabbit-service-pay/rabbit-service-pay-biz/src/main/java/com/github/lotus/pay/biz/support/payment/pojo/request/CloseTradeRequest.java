package com.github.lotus.pay.biz.support.payment.pojo.request;

import com.github.lotus.pay.biz.support.payment.helper.RequestHelper;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import in.hocg.payment.alipay.v2.request.AliPayRequest;
import in.hocg.payment.alipay.v2.request.TradeCloseRequest;
import in.hocg.payment.wxpay.v2.request.CloseOrderRequest;
import in.hocg.payment.wxpay.v2.request.WxPayRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Builder
@ApiModel("关闭交易单")
@EqualsAndHashCode(callSuper = true)
public class CloseTradeRequest extends AbsRequest {
    @ApiModelProperty
    protected final ConfigStorageDto configStorage;
    @ApiModelProperty(value = "交易单号(网关)", required = true)
    private final String tradeSn;

    public boolean request() {
        switch (this.getPlatform()) {
            case WxPay: {
                return RequestHelper.isSuccess(this.request(this.wxPayRequest()));
            }
            case AliPay: {
                return RequestHelper.isSuccess(this.request(this.aliPayRequest()));
            }
            default:
                throw new UnsupportedOperationException();
        }
    }

    private AliPayRequest aliPayRequest() {
        final TradeCloseRequest request = new TradeCloseRequest();
        request.setBizContent2(new TradeCloseRequest.BizContent().setOutTradeNo(getTradeSn()));
        return request;
    }

    private WxPayRequest wxPayRequest() {
        final CloseOrderRequest request = new CloseOrderRequest();
        request.setOutTradeNo(getTradeSn());
        return request;
    }
}
