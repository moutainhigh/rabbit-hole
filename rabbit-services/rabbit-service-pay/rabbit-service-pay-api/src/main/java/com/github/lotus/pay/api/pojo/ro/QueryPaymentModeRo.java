package com.github.lotus.pay.api.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/7/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class QueryPaymentModeRo {
    @NotNull(message = "应用编号不能为空")
    @ApiModelProperty(value = "接入应用编号", required = true)
    private String appid;
    @NotNull(message = "场景码不能为空")
    @ApiModelProperty(value = "场景码", required = true)
    private String sceneCode;
}
