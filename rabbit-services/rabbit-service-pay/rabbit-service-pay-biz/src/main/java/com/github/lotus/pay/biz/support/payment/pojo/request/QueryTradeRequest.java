package com.github.lotus.pay.biz.support.payment.pojo.request;

import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import com.github.lotus.pay.biz.support.payment.pojo.response.QueryTradeResponse;
import in.hocg.payment.alipay.v2.request.AliPayRequest;
import in.hocg.payment.alipay.v2.request.TradeQueryRequest;
import in.hocg.payment.alipay.v2.response.TradeQueryResponse;
import in.hocg.payment.wxpay.v2.request.OrderQueryRequest;
import in.hocg.payment.wxpay.v2.request.WxPayRequest;
import in.hocg.payment.wxpay.v2.response.OrderQueryResponse;
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
public class QueryTradeRequest extends AbsRequest {
    @ApiModelProperty
    protected final ConfigStorageDto configStorage;
    @NonNull
    @ApiModelProperty(value = "交易单号(第三方)", required = true)
    private String tradeNo;

    public QueryTradeResponse request() {
        final QueryTradeResponse result = new QueryTradeResponse();
        switch (getPlatform()) {
            case WxPay: {
                final OrderQueryResponse response = this.request(this.wxPayRequest());
                break;
            }
            case AliPay: {
                final TradeQueryResponse response = this.request(this.aliPayRequest());
                break;
            }
            default:
                throw new UnsupportedOperationException();
        }
        return result;
    }

    private AliPayRequest aliPayRequest() {
        final TradeQueryRequest request = new TradeQueryRequest();
        request.setBizContent2(new TradeQueryRequest.BizContent().setTradeNo(tradeNo));
        return request;
    }

    private WxPayRequest wxPayRequest() {
        final OrderQueryRequest request = new OrderQueryRequest();
        request.setTransactionId(tradeNo);
        return request;
    }
}
