package com.github.lotus.bmw.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GetTradeRo extends AccessRo {
    @ApiModelProperty(value = "接入商户订单号", required = true)
    private String outTradeNo;
    @ApiModelProperty(value = "订单号", required = true)
    private String tradeNo;

}
