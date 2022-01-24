package in.hocg.rabbit.mall.biz.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [订单模块] 退货申请
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_maintain")
public class OrderMaintain extends LogicDeletedEntity<OrderMaintain> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("退款申请编号")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("售后类型：full_refund=>退货退款 ")
    @TableField("type")
    private String type;

    @ApiModelProperty("申请状态")
    @TableField("status")
    private String status;
    @ApiModelProperty("评价状态")
    @TableField("comment_status")
    private String commentStatus;

    @ApiModelProperty("退款用户")
    @TableField("owner_user_id")
    private Long ownerUserId;
    @ApiModelProperty("订单详情")
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
    @TableField("handler_user_id")
    private Long handlerUserId;
    @ApiModelProperty("处理时间")
    @TableField("handle_at")
    private LocalDateTime handleAt;
    @ApiModelProperty("处理备注")
    @TableField("handle_remark")
    private String handleRemark;



}
