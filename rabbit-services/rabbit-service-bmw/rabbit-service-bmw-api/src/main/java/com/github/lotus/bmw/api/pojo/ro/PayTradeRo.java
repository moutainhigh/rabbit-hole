package com.github.lotus.bmw.api.pojo.ro;

import com.github.lotus.common.datadict.bmw.UseScenes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Data
@Accessors(chain = true)
@ApiModel(description = "支付请求")
@EqualsAndHashCode(callSuper = true)
public class PayTradeRo extends AccessRo {
    @ApiModelProperty(value = "交易单号(接入商户)", required = true)
    private String outOrderNo;
    @ApiModelProperty(value = "交易单号", required = true)
    private String orderNo;
    @NotNull(message = "支付方式不能为空")
    @ApiModelProperty(value = "支付方式", required = true)
    private String payType;
    @NotNull(message = "用户")
    @ApiModelProperty(value = "用户", required = true)
    private Long userId;
    @NotNull(message = "使用场景")
    @ApiModelProperty(value = "使用场景", required = true)
    private String useScenes = UseScenes.None.getCodeStr();

    @ApiModelProperty(hidden = true)
    private String clientIp;
}
