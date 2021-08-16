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
public class TradeStatusSyncVo {
    @ApiModelProperty("交易单号")
    private String orderNo;
    @ApiModelProperty("交易单号(接入商户)")
    private String outOrderNo;
    @ApiModelProperty("是否担保交易")
    private Boolean guaranteeFlag;
    @ApiModelProperty("交易金额")
    private BigDecimal tradeAmt;
    @ApiModelProperty("退款金额")
    private BigDecimal refundAmt;
    @ApiModelProperty("交易状态: processing=>进行中; payed=>已支付; cancelled=>已取消; closed=>已关闭")
    private String status;
    @ApiModelProperty("计划关单时间")
    private LocalDateTime planCloseAt;
    @ApiModelProperty("订单描述")
    private String orderDesc;
    @ApiModelProperty("附加参数")
    private String attach;
    @ApiModelProperty("关单原因")
    private String reason;
    @ApiModelProperty("完结通知地址")
    private String notifyUrl;
    @ApiModelProperty("支付后前端回跳地址")
    private String frontJumpUrl;
    @ApiModelProperty("完成时间")
    private LocalDateTime finishedAt;
    @ApiModelProperty("支付商户(最终)")
    private Long paymentMchId;
    @ApiModelProperty("用户实际支付金额(最终)")
    private BigDecimal realPayAmt;
    @ApiModelProperty("支付类型(最终)")
    private String payType;
}
