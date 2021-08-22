package com.github.lotus.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/3/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class RefundApplyRo {
    @NotNull
    @ApiModelProperty("Id")
    private Long orderItemId;
    @NotNull
    @ApiModelProperty("退货原因")
    private String refundReason;
    @NotNull
    @ApiModelProperty("备注")
    private String refundRemark;

    @ApiModelProperty(hidden = true)
    private Long userId;
}
