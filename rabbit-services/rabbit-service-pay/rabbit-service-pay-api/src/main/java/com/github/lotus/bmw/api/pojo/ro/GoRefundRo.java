package com.github.lotus.bmw.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "发起退款")
@EqualsAndHashCode(callSuper = true)
public class GoRefundRo extends AccessRo {
    @ApiModelProperty(value = "交易单号(接入商户), outTradeOrderNo/tradeOrderNo 二选一", required = true)
    private String outTradeOrderNo;
    @ApiModelProperty(value = "交易单号, outTradeOrderNo/tradeOrderNo 二选一", required = true)
    private String tradeOrderNo;
    @ApiModelProperty(value = "退款单号(接入商户)", required = true)
    private String outOrderNo;
    @NotNull(message = "退款金额错误")
    @ApiModelProperty(value = "退款金额", required = true)
    private BigDecimal refundAmt;
    @ApiModelProperty(value = "退款原因")
    private String refundReason;
    @ApiModelProperty(value = "附加参数")
    private String attach;
    @ApiModelProperty(value = "通知地址")
    private String notifyUrl;
}
