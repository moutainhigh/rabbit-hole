package com.github.lotus.bmw.biz.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 * 接入应用 支付场景 交易单号
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class CashierInfoDto {
    @ApiModelProperty("接入商户")
    private Long accessMchId;
    @ApiModelProperty("支付场景")
    private Long paySceneId;
    @ApiModelProperty("交易单")
    private Long tradeOrderId;
}
