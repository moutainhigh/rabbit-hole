package com.github.lotus.bmw.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class RefundStatusSyncVo {
    @ApiModelProperty("退款单号")
    private String refundNo;
    @ApiModelProperty("退款单号(接入商户)")
    private String outRefundNo;
    @ApiModelProperty("退款金额")
    private BigDecimal refundAmt;
    @ApiModelProperty("完结通知地址")
    private String notifyUrl;
    @ApiModelProperty("附加参数")
    private String attach;
    @ApiModelProperty("退款原因")
    private String reason;
    @ApiModelProperty("退款状态")
    private String status;
    @ApiModelProperty("完成时间")
    private LocalDateTime finishedAt;
}
