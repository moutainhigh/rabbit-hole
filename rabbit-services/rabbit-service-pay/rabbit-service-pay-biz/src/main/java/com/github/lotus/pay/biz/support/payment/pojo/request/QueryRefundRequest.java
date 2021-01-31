package com.github.lotus.pay.biz.support.payment.pojo.request;

import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import com.github.lotus.pay.biz.support.payment.pojo.response.QueryRefundResponse;
import in.hocg.payment.alipay.v2.request.AliPayRequest;
import in.hocg.payment.alipay.v2.request.TradeRefundRequest;
import in.hocg.payment.alipay.v2.response.TradeRefundResponse;
import in.hocg.payment.wxpay.v2.request.RefundQueryRequest;
import in.hocg.payment.wxpay.v2.request.WxPayRequest;
import in.hocg.payment.wxpay.v2.response.RefundQueryResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Builder
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class QueryRefundRequest extends AbsRequest {
    @ApiModelProperty
    protected final ConfigStorageDto configStorage;
    @NonNull
    @ApiModelProperty(value = "退款单号(第三方)", required = true)
    private String refundTradeNo;

    public QueryRefundResponse request() {
        final QueryRefundResponse result = new QueryRefundResponse();
        switch (getPlatform()) {
            case WxPay: {
                final RefundQueryResponse response = this.request(this.wxPayRequest());
                break;
            }
            case AliPay: {
                final TradeRefundResponse response = this.request(this.aliPayRequest());
                break;
            }
            default:
                throw new UnsupportedOperationException();
        }
        return result;
    }


    private AliPayRequest aliPayRequest() {
        final TradeRefundRequest request = new TradeRefundRequest();
        request.setBizContent2(new TradeRefundRequest.BizContent().setTradeNo(refundTradeNo));
        return request;
    }

    private WxPayRequest wxPayRequest() {
        final RefundQueryRequest request = new RefundQueryRequest();
        request.setRefundId(refundTradeNo);
        return request;
    }

}
