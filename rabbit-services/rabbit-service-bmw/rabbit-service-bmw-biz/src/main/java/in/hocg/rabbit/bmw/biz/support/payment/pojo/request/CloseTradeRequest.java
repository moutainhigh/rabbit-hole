package in.hocg.rabbit.bmw.biz.support.payment.pojo.request;

import in.hocg.rabbit.bmw.biz.docking.alipay.AliPayHelper;
import in.hocg.rabbit.bmw.biz.docking.wxpay.WxPayHelper;
import in.hocg.rabbit.bmw.biz.support.payment.ConfigStorageDto;
import in.hocg.rabbit.common.datadict.bmw.PaymentMchType;
import in.hocg.rabbit.common.utils.Rules;
import in.hocg.payment.alipay.v2.request.AliPayRequest;
import in.hocg.payment.alipay.v2.request.TradeCloseRequest;
import in.hocg.payment.wxpay.v2.request.CloseOrderRequest;
import in.hocg.payment.wxpay.v2.request.WxPayRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

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
        Optional<Boolean> opt = Rules.create()
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> WxPayHelper.isSuccess(this.request(this.wxPayRequest()))))
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> AliPayHelper.isSuccess(this.request(this.aliPayRequest()))))
            .of(this.getPaymentMchType());
        return opt.orElseThrow(UnsupportedOperationException::new);
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
