package com.github.lotus.bmw.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class GetCashierRo extends AccessRo {
    @ApiModelProperty(value = "交易单号(接入商户)", required = true)
    private String outTradeNo;
    @ApiModelProperty(value = "交易单号", required = true)
    private String tradeNo;
    @NotNull(message = "支付场景不能为空")
    @ApiModelProperty(value = "支付场景", required = true)
    private String sceneCode;
}
