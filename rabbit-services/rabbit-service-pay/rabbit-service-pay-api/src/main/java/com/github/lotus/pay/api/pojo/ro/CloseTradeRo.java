package com.github.lotus.pay.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class CloseTradeRo {
    @NotNull(message = "交易单据错误")
    private String tradeSn;

    @NotNull(message = "IP错误")
    @ApiModelProperty(hidden = true)
    private String clientIp;
}
