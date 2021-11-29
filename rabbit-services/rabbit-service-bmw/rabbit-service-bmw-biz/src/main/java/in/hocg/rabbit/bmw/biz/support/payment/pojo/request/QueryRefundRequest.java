package in.hocg.rabbit.bmw.biz.support.payment.pojo.request;

import in.hocg.rabbit.bmw.biz.support.payment.ConfigStorageDto;
import in.hocg.rabbit.bmw.biz.support.payment.pojo.response.QueryRefundResponse;
import in.hocg.rabbit.common.datadict.bmw.PaymentMchType;
import in.hocg.rabbit.common.utils.Rules;
import in.hocg.payment.alipay.v2.request.AliPayRequest;
import in.hocg.payment.alipay.v2.request.TradeRefundRequest;
import in.hocg.payment.wxpay.v2.request.RefundQueryRequest;
import in.hocg.payment.wxpay.v2.request.WxPayRequest;
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
public class QueryRefundRequest extends AbsRequest {
    @ApiModelProperty
    protected final ConfigStorageDto configStorage;
    @NonNull
    @ApiModelProperty(value = "退款单号(第三方)", required = true)
    private String refundTradeNo;

    public QueryRefundResponse request() {
        Optional<QueryRefundResponse> opt = Rules.create()
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> this.request(this.wxPayRequest())))
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> this.request(this.aliPayRequest())))
            .of(this.getPaymentMchType());
        return opt.orElseThrow(UnsupportedOperationException::new);
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
