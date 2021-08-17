package com.github.lotus.bmw.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/8/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "关单")
public class CloseTradeRo {
    @ApiModelProperty("交易单")
    private Long tradeOrderId;

    @ApiModelProperty(value = "用户", required = true, hidden = true)
    private Long userId;
    @ApiModelProperty(value = "发起支付的客户端", hidden = true)
    private String clientIp;
}
