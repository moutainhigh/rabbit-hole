package com.github.lotus.mall.biz.entity;

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
 * [订单模块] 退货申请
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_refund_apply")
public class OrderRefundApply extends AbstractEntity<OrderRefundApply> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("退款申请编号")
    @TableField("refund_apply_no")
    private String refundApplyNo;
    @ApiModelProperty("申请状态：[0:待处理；1:退货中；2:已完成；3:已拒绝]")
    @TableField("apply_status")
    private Integer applyStatus;
    @ApiModelProperty("订单商品ID")
    @TableField("order_item_id")
    private Long orderItemId;
    @ApiModelProperty("退货数量")
    @TableField("refund_quantity")
    private Integer refundQuantity;
    @ApiModelProperty("退款金额")
    @TableField("refund_amt")
    private BigDecimal refundAmt;
    @ApiModelProperty("退货原因")
    @TableField("refund_reason")
    private String refundReason;
    @ApiModelProperty("退货备注")
    @TableField("refund_remark")
    private String refundRemark;
    @ApiModelProperty("处理人")
    @TableField("handler_id")
    private Long handlerId;
    @ApiModelProperty("处理时间")
    @TableField("handle_at")
    private LocalDateTime handleAt;
    @ApiModelProperty("处理备注")
    @TableField("handle_remark")
    private String handleRemark;
    @ApiModelProperty("收货人")
    @TableField("receiver_id")
    private Long receiverId;
    @ApiModelProperty("收货时间")
    @TableField("receive_at")
    private LocalDateTime receiveAt;
    @ApiModelProperty("收货备注")
    @TableField("receive_remark")
    private String receiveRemark;
    @TableField("creator")
    private Long creator;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("last_updater")
    private Long lastUpdater;
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;



}
