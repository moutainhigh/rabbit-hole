package com.github.lotus.pay.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class TradeOrdinaryVo {

    private Long id;
    @ApiModelProperty("接入方应用")
    private Long accessAppId;
    @ApiModelProperty("最终接入平台(第三方回调时填充)")
    private Long accessPlatformId;
    @ApiModelProperty("交易单号(接入方)")
    private String outTradeSn;
    @ApiModelProperty("交易单号(网关)")
    private String tradeSn;
    @ApiModelProperty("交易总金额")
    private BigDecimal totalFee;
    @ApiModelProperty("交易状态: init=>等待支付; wait_pay=>待付款; success=>完成支付; close=>交易已关闭; fail=>支付失败")
    private String tradeStatus;
    @ApiModelProperty("通知接入应用的地址")
    private String notifyUrl;
    @ApiModelProperty("最终买家实际支付金额(第三方回调时填充)")
    private BigDecimal buyerPayFee;
    @ApiModelProperty("最终支付方式(第三方回调时填充)")
    private String payMode;
    @ApiModelProperty("最终第三方的交易单号(第三方回调填充)")
    private String tradeNo;
    @ApiModelProperty("最终第三方支付成功的时间(第三方回调填充)")
    private LocalDateTime paymentAt;
    @ApiModelProperty("接收到第三方支付通知的时间")
    private LocalDateTime notifyAt;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建的IP")
    private String createdIp;
    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
    @ApiModelProperty("更新的IP")
    private String updatedIp;
    @ApiModelProperty("通知接入应用并完成交易的时间")
    private LocalDateTime finishAt;
    @ApiModelProperty("账单过期时间")
    private LocalDateTime expireAt;
}
