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
 * [订单模块] 订单优惠详项表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_discount")
public class OrderDiscount extends LogicDeletedEntity<OrderDiscount> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("类型: coupon_discount=>优惠券优惠; adjustment=>后台调价;")
    @TableField("type")
    private String type;
    @ApiModelProperty("优惠标题")
    @TableField("title")
    private String title;
    @ApiModelProperty("优惠金额")
    @TableField("discount_amt")
    private BigDecimal discountAmt;
    @ApiModelProperty("优惠对象")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("优惠对象类型")
    @TableField("ref_type")
    private String refType;



}
