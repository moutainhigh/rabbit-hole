package com.github.lotus.bmw.biz.pojo.ro;

import com.github.lotus.bmw.api.pojo.ro.AccessRo;
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
public class GoPayRo {
    @NotNull(message = "交易单不能为空")
    @ApiModelProperty(value = "交易单", required = true)
    private Long tradeOrderId;
    @NotNull(message = "支付场景不能为空")
    @ApiModelProperty(value = "支付场景", required = true)
    private Long paySceneId;
    @NotNull(message = "支付方式不能为空")
    @ApiModelProperty(value = "支付方式", required = true)
    private String payType;

    @NotNull(message = "账号使用场景")
    @ApiModelProperty(value = "账号使用场景", required = true)
    private String useScenes = UseScenes.None.getCodeStr();
    @ApiModelProperty(value = "用户", required = true, hidden = true)
    private Long userId;
    @ApiModelProperty(value = "发起支付的客户端", hidden = true)
    private String clientIp;
    @ApiModelProperty(value = "支付记录", hidden = true)
    private Long payRecordId;
    @ApiModelProperty(value = "支付商户", hidden = true)
    private Long paymentMchId;
    @ApiModelProperty(value = "支付商户类型", hidden = true)
    private String paymentMchType;
}