package com.github.lotus.bmw.biz.support.payment.pojo.request;

import com.github.lotus.bmw.biz.docking.alipay.AliPayHelper;
import com.github.lotus.bmw.biz.docking.wxpay.WxPayHelper;
import com.github.lotus.bmw.biz.support.payment.ConfigStorageDto;
import com.github.lotus.bmw.biz.support.payment.pojo.response.GoRefundResponse;
import com.github.lotus.common.datadict.bmw.PaymentMchType;
import com.github.lotus.common.utils.Rules;
import in.hocg.payment.alipay.v2.request.AliPayRequest;
import in.hocg.payment.alipay.v2.request.TradeRefundRequest;
import in.hocg.payment.alipay.v2.response.TradeRefundResponse;
import in.hocg.payment.wxpay.v2.request.PayRefundRequest;
import in.hocg.payment.wxpay.v2.request.WxPayRequest;
import in.hocg.payment.wxpay.v2.response.PayRefundResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Optional;

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
public class GoRefundRequest extends AbsRequest {
    @ApiModelProperty
    protected final ConfigStorageDto configStorage;
    @ApiModelProperty("交易单号(网关)")
    private String tradeSn;
    @ApiModelProperty("交易流水号(第三方)")
    private final String tradeNo;
    @ApiModelProperty("交易总金额")
    private BigDecimal totalFee;
    @ApiModelProperty("退款流水号")
    private final String refundSn;
    @ApiModelProperty("退款金额")
    private final BigDecimal refundFee;

    private AliPayRequest aliRefundRequest() {
        TradeRefundRequest request = new TradeRefundRequest();
        request.setBizContent2(new TradeRefundRequest.BizContent()
            .setRefundAmount(String.valueOf(getRefundFee()))
            .setOutTradeNo(getRefundSn())
            .setTradeNo(getTradeNo()));
        request.setNotifyUrl(this.getNotifyUrl());
        return request;
    }

    private WxPayRequest wxRefundRequest() {
        final String refundAccount = String.valueOf(this.getRefundFee().multiply(BigDecimal.valueOf(100L)));
        final String totalFe = String.valueOf(this.getTotalFee().multiply(BigDecimal.valueOf(100L)));

        PayRefundRequest request = new PayRefundRequest();
        request.setOutTradeNo(getTradeSn());
        request.setTransactionId(getTradeNo());
        request.setOutRefundNo(getRefundSn());
        request.setTotalFee(totalFe);
        request.setRefundAccount(refundAccount);
        request.setNotifyUrl(this.getNotifyUrl());
        return request;
    }

    public GoRefundResponse request() {
        final GoRefundResponse result = new GoRefundResponse();
        Optional<GoRefundResponse> opt = Rules.create()
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> {
                final WxPayRequest request = this.wxRefundRequest();
                final PayRefundResponse response = this.request(request);
                return result.setRefundTradeNo(response.getRefundId());
            }))
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> {
                final AliPayRequest request = this.aliRefundRequest();
                final TradeRefundResponse response = this.request(request);
                result.setRefundTradeNo(response.getTradeNo());
                return result;
            }))
            .of(this.getPaymentMchType());
        return opt.orElseThrow(UnsupportedOperationException::new);
    }

    private String getNotifyUrl() {
        return getRefundNotifyUrl();
    }

}
