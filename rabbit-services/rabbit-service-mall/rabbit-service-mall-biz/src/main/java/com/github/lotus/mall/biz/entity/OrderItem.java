package com.github.lotus.mall.biz.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * [订单模块] 订单商品表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_item")
public class OrderItem extends AbstractEntity<OrderItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("订单ID")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("商品ID")
    @TableField("product_id")
    private Long productId;
    @ApiModelProperty("商品名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("销售单价")
    @TableField("unit_price")
    private BigDecimal unitPrice;
    @ApiModelProperty("购买数量")
    @TableField("quantity")
    private Integer quantity;
    @ApiModelProperty("商品主图")
    @TableField("image_url")
    private String imageUrl;
    @ApiModelProperty("商品SKU ID")
    @TableField("sku_id")
    private Long skuId;
    @ApiModelProperty("商品SKU条码")
    @TableField("sku_code")
    private String skuCode;
    @ApiModelProperty("商品规格:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    @TableField("sku_spec_data")
    private String skuSpecData;

    @ApiModelProperty("优惠分解金额(不含后台调整)")
    @TableField("discount_amt")
    private BigDecimal discountAmt;
    @ApiModelProperty("后台调整优惠")
    @TableField("adjustment_discount_amt")
    private BigDecimal adjustmentDiscountAmt;
    @ApiModelProperty("[计算型]原总价=销售价格x购买数量")
    @TableField("total_amt")
    private BigDecimal totalAmt;
    @ApiModelProperty("[计算型]该商品经过优惠后的分解金额(实际支付金额)=原总价-后台调整优惠-优惠分解金额")
    @TableField("user_pay_amt")
    private BigDecimal userPayAmt;



}
