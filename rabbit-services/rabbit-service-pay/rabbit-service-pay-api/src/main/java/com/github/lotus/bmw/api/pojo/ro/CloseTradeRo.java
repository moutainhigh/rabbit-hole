package com.github.lotus.bmw.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class CloseTradeRo extends AccessRo {
    @ApiModelProperty(value = "接入商户交易单号", required = true)
    private String outOrderNo;
    @ApiModelProperty(value = "交易单号", required = true)
    private String orderNo;

    @NotNull(message = "IP错误")
    @ApiModelProperty(hidden = true)
    private String clientIp;
}
