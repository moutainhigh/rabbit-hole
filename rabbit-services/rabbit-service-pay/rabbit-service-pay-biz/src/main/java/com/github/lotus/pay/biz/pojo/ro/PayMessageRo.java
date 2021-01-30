package com.github.lotus.pay.biz.pojo.ro;

import com.github.lotus.common.datadict.bmw.PayMode;
import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.common.datadict.bmw.TradeStatus;
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
public class PayMessageRo {
    @ApiModelProperty(value = "接入应用", required = true)
    private String accessAppSn;
    @ApiModelProperty(value = "支付平台", required = true)
    private PaymentPlatform platformType;
    @ApiModelProperty(value = "交易单号(第三方)", required = true)
    private String tradeNo;
    @ApiModelProperty(value = "交易单号(网关)", required = true)
    private String tradeSn;
    @ApiModelProperty(value = "付款时间", required = true)
    private LocalDateTime paymentAt;
    @ApiModelProperty(value = "交易总金额", required = true)
    private BigDecimal totalFee;
    @ApiModelProperty(value = "交易状态", required = true)
    private TradeStatus tradeStatus;
    @ApiModelProperty("买家实际支付金额")
    private BigDecimal buyerPayFee;
    @ApiModelProperty("支付方式")
    private PayMode paymentMode;

    @ApiModelProperty(hidden = true)
    private String clientIp;
}
