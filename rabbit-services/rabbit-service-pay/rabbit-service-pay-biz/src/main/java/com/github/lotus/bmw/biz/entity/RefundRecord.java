package com.github.lotus.bmw.biz.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [支付模块] 退款记录表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_refund_record")
public class RefundRecord extends AbstractEntity<RefundRecord> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("接入商户")
    @TableField("access_mch_id")
    private Long accessMchId;
    @ApiModelProperty("交易单")
    @TableField("trade_order_id")
    private Long tradeOrderId;
    @ApiModelProperty("退款单号")
    @TableField("order_no")
    private String orderNo;
    @ApiModelProperty("退款单号(接入商户)")
    @TableField("out_order_no")
    private String outOrderNo;
    @ApiModelProperty("退款金额")
    @TableField("refund_amt")
    private BigDecimal refundAmt;
    @ApiModelProperty("退款账户")
    @TableField("refund_act_id")
    private Long refundActId;
    @ApiModelProperty("完结通知地址")
    @TableField("notify_url")
    private String notifyUrl;
    @ApiModelProperty("附加参数")
    @TableField("attach")
    private String attach;
    @ApiModelProperty("退款原因")
    @TableField("reason")
    private String reason;
    @ApiModelProperty("退款状态")
    @TableField("status")
    private String status;
    @ApiModelProperty("完成时间")
    @TableField("finished_at")
    private LocalDateTime finishedAt;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;
    @ApiModelProperty("更新时间")
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    @TableField("last_updater")
    private Long lastUpdater;


}
