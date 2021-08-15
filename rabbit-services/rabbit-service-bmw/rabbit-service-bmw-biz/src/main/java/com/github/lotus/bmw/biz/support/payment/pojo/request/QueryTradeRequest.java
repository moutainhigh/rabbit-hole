package com.github.lotus.bmw.biz.support.payment.pojo.request;

import com.github.lotus.bmw.biz.support.payment.ConfigStorageDto;
import com.github.lotus.bmw.biz.support.payment.pojo.response.QueryRefundResponse;
import com.github.lotus.bmw.biz.support.payment.pojo.response.QueryTradeResponse;
import com.github.lotus.common.datadict.bmw.PaymentMchType;
import com.github.lotus.common.utils.Rules;
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

import java.util.Optional;

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
        Optional<QueryTradeResponse> opt = Rules.create()
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> this.request(this.wxPayRequest())))
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> this.request(this.aliPayRequest())))
            .of(this.getPaymentMchType());
        return opt.orElseThrow(UnsupportedOperationException::new);
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
