package com.github.lotus.bmw.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel("创建交易单")
@EqualsAndHashCode(callSuper = true)
public class CreateTradeRo extends AccessRo {
    @NotNull(message = "接入商户订单号不能为空")
    @ApiModelProperty(value = "接入商户订单号", required = true)
    private String outTradeNo;
    @NotNull(message = "交易总金额不能为空")
    @ApiModelProperty(value = "交易总金额", required = true)
    private BigDecimal tradeAmt;
    @NotNull(message = "是否担保交易不能为空")
    @ApiModelProperty(value = "是否担保交易", required = true)
    private Boolean isGuarantee = Boolean.FALSE;

    @ApiModelProperty("附加参数")
    private String attach;
    @ApiModelProperty("前端跳转地址")
    private String frontJumpUrl;
    @ApiModelProperty("通知地址")
    private String notifyUrl;
    @ApiModelProperty("订单描述")
    private String orderDesc;
    @ApiModelProperty("计划关单时间")
    private LocalDateTime planCloseAt;
    @ApiModelProperty(hidden = true)
    private String clientIp;
}
