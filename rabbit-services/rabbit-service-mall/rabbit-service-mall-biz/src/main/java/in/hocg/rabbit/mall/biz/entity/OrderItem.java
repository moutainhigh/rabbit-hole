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
 * [订单模块] 订单详情表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_item")
public class OrderItem extends LogicDeletedEntity<OrderItem> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("订单ID")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("商品类型: sku=>商品")
    @TableField("product_type")
    private String productType;
    @ApiModelProperty("商品ID")
    @TableField("product_id")
    private Long productId;
    @ApiModelProperty("商品名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("购买数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("销售价")
    @TableField("sale_price")
    private BigDecimal salePrice;
    @ApiModelProperty("[计算型]原总价=销售价格x购买数量")
    @TableField("total_amt")
    private BigDecimal totalAmt;
    @ApiModelProperty("优惠金额")
    @TableField("discount_amt")
    private BigDecimal discountAmt;
    @ApiModelProperty("[计算型]优惠后金额=原总价-优惠金额")
    @TableField("real_amt")
    private BigDecimal realAmt;

    @ApiModelProperty("评价状态")
    @TableField("comment_status")
    private String commentStatus;
    @ApiModelProperty("评价时间")
    @TableField("commented_at")
    private LocalDateTime commentedAt;
    @ApiModelProperty("售后状态")
    @TableField("maintain_status")
    private String maintainStatus;
    @ApiModelProperty("售后截止时间")
    @TableField("plan_maintain_at")
    private LocalDateTime planMaintainAt;

}
