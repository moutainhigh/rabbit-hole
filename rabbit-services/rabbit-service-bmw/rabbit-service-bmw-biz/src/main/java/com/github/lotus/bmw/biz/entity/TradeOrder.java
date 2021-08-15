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
 * [支付模块] 交易单表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_trade_order")
public class TradeOrder extends AbstractEntity<TradeOrder> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("接入商户")
    @TableField("access_mch_id")
    private Long accessMchId;
    @ApiModelProperty("交易单号")
    @TableField("order_no")
    private String orderNo;
    @ApiModelProperty("交易单号(接入商户)")
    @TableField("out_order_no")
    private String outOrderNo;
    @ApiModelProperty("是否担保交易")
    @TableField("guarantee_flag")
    private Boolean guaranteeFlag;
    @ApiModelProperty("交易金额")
    @TableField("trade_amt")
    private BigDecimal tradeAmt;
    @ApiModelProperty("退款金额")
    @TableField("refund_amt")
    private BigDecimal refundAmt;
    @ApiModelProperty("交易状态: processing=>进行中; payed=>已支付; cancelled=>已取消; closed=>已关闭")
    @TableField("status")
    private String status;
    @ApiModelProperty("计划关单时间")
    @TableField("plan_close_at")
    private LocalDateTime planCloseAt;
    @ApiModelProperty("订单描述")
    @TableField("order_desc")
    private String orderDesc;
    @ApiModelProperty("附加参数")
    @TableField("attach")
    private String attach;
    @ApiModelProperty("关单原因")
    @TableField("reason")
    private String reason;
    @ApiModelProperty("完结通知地址")
    @TableField("notify_url")
    private String notifyUrl;
    @ApiModelProperty("支付后前端回跳地址")
    @TableField("front_jump_url")
    private String frontJumpUrl;
    @ApiModelProperty("完成时间")
    @TableField("finished_at")
    private LocalDateTime finishedAt;
    @ApiModelProperty("支付商户(最终)")
    @TableField("payment_mch_id")
    private Long paymentMchId;
    @ApiModelProperty("用户实际支付金额(最终)")
    @TableField("real_pay_amt")
    private BigDecimal realPayAmt;
    @ApiModelProperty("支付类型(最终)")
    @TableField("pay_type")
    private String payType;
    @ApiModelProperty("支付账户(最终)")
    @TableField("pay_act_id")
    private Long payActId;
    @ApiModelProperty("支付记录(最终)")
    @TableField("pay_record_id")
    private Long payRecordId;

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
