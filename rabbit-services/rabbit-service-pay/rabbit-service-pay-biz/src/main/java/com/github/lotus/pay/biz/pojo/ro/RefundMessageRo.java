package com.github.lotus.pay.biz.pojo.ro;

import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.common.datadict.bmw.RefundStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RefundMessageRo {
    @ApiModelProperty(value = "接入应用", required = true)
    private Long accessPlatformId;
    @ApiModelProperty(value = "支付平台", required = true)
    private PaymentPlatform platformType;
    @ApiModelProperty(value = "退款单号(第三方)", required = true)
    private String refundTradeNo;
    @ApiModelProperty(value = "退款单号(网关)", required = true)
    private String refundSn;
    @ApiModelProperty(value = "付款时间", required = true)
    private LocalDateTime refundAt;
    @ApiModelProperty("申请退款金额")
    private BigDecimal refundFee;
    @ApiModelProperty("实际退款金额")
    private BigDecimal settlementRefundFee;
    @ApiModelProperty("退款状态")
    private RefundStatus refundStatus;

    @ApiModelProperty(hidden = true)
    private String clientIp;
}
