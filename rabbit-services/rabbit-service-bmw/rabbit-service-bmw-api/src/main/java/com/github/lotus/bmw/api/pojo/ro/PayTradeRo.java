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
    @NotNull(message = "支付场景不能为空")
    @ApiModelProperty(value = "支付场景", required = true)
    private String sceneCode;

    @NotNull(message = "用户")
    @ApiModelProperty(value = "用户", required = true)
    private Long userId;
    @NotNull(message = "账号使用场景")
    @ApiModelProperty(value = "账号使用场景", required = true)
    private String useScenes = UseScenes.None.getCodeStr();

    @ApiModelProperty(value = "发起支付的客户端", hidden = true)
    private String clientIp;
    @ApiModelProperty(value = "支付记录", hidden = true)
    private Long payRecordId;
    @ApiModelProperty(value = "支付商户类型", hidden = true)
    private String paymentMchType;
}
