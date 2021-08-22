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
 * [订单模块] 订单优惠详项表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_discount")
public class OrderDiscount extends AbstractEntity<OrderDiscount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("订单")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("优惠标题")
    @TableField("title")
    private String title;
    @ApiModelProperty("优惠金额")
    @TableField("discount_amt")
    private BigDecimal discountAmt;
    @ApiModelProperty("对象(优惠)")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("对象类型")
    @TableField("ref_type")
    private String refType;



}
