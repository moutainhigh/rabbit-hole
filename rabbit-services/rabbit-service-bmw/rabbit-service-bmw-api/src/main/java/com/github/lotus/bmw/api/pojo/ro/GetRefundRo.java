package com.github.lotus.bmw.api.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class GetRefundRo extends AccessRo {
    @ApiModelProperty(value = "退款单号(接入商户)", required = true)
    private String outRefundNo;
    @ApiModelProperty(value = "退款单号", required = true)
    private String refundNo;
}
