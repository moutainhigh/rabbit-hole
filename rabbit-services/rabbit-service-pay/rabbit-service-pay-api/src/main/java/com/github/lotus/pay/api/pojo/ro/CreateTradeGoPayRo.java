package com.github.lotus.pay.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/1/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel("创建交易单")
public class CreateTradeGoPayRo extends CreateTradeRo {
    @ApiModelProperty(value = "[可选]微信支付")
    private String wxOpenId;

    @NotNull(message = "支付方式不能为空")
    @ApiModelProperty(value = "支付方式", required = true)
    private String payMode;
}
