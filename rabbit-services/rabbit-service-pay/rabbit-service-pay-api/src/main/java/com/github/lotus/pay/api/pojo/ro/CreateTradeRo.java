package com.github.lotus.pay.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel("创建交易单")
public class CreateTradeRo {
    @NotNull(message = "应用编号不能为空")
    @ApiModelProperty(value = "接入应用编号", required = true)
    private String appid;
    @NotNull(message = "应用订单号不能为空")
    @ApiModelProperty(value = "接入应用订单号", required = true)
    private String outTradeSn;
    @NotNull(message = "交易总金额不能为空")
    @ApiModelProperty(value = "交易总金额", required = true)
    private BigDecimal totalFee;
    @ApiModelProperty("通知地址")
    private String notifyUrl;

    @ApiModelProperty(hidden = true)
    private String clientIp;
}
