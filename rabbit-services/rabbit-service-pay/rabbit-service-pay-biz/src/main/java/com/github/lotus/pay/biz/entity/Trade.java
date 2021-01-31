package com.github.lotus.pay.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * [支付网关] 交易账单表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_trade")
public class Trade extends AbstractEntity<Trade> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("接入方应用")
    @TableField("access_app_id")
    private Long accessAppId;
    @ApiModelProperty("最终接入平台(第三方回调时填充)")
    @TableField("access_platform_id")
    private Long accessPlatformId;
    @ApiModelProperty("交易单号(接入方)")
    @TableField("out_trade_sn")
    private String outTradeSn;
    @ApiModelProperty("交易单号(网关)")
    @TableField("trade_sn")
    private String tradeSn;
    @ApiModelProperty("交易总金额")
    @TableField("total_fee")
    private BigDecimal totalFee;
    @ApiModelProperty("交易状态: init=>等待支付; wait_pay=>待付款; success=>完成支付; close=>交易已关闭; fail=>支付失败")
    @TableField("trade_status")
    private String tradeStatus;
    @ApiModelProperty("通知接入应用的地址")
    @TableField("notify_url")
    private String notifyUrl;
    @ApiModelProperty("最终买家实际支付金额(第三方回调时填充)")
    @TableField("buyer_pay_fee")
    private BigDecimal buyerPayFee;
    @ApiModelProperty("最终支付方式(第三方回调时填充)")
    @TableField("pay_mode")
    private String payMode;
    @ApiModelProperty("最终第三方的交易单号(第三方回调填充)")
    @TableField("trade_no")
    private String tradeNo;
    @ApiModelProperty("最终第三方支付成功的时间(第三方回调填充)")
    @TableField("payment_at")
    private LocalDateTime paymentAt;
    @ApiModelProperty("接收到第三方支付通知的时间")
    @TableField("notify_at")
    private LocalDateTime notifyAt;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建的IP")
    @TableField("created_ip")
    private String createdIp;
    @ApiModelProperty("更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
    @ApiModelProperty("更新的IP")
    @TableField("updated_ip")
    private String updatedIp;
    @ApiModelProperty("通知接入应用并完成交易的时间")
    @TableField("finish_at")
    private LocalDateTime finishAt;
    @ApiModelProperty("账单过期时间")
    @TableField("expire_at")
    private LocalDateTime expireAt;



}
