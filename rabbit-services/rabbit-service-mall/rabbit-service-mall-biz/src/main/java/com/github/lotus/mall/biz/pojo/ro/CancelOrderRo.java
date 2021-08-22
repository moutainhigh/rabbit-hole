package com.github.lotus.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CancelOrderRo {
    @NotNull
    private Long orderId;

    @ApiModelProperty(hidden = true)
    private Long userId;
}
