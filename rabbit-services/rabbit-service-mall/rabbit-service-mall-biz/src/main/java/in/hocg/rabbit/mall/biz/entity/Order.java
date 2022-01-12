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
 * [订单模块] 订单主表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order")
public class Order extends LogicDeletedEntity<Order> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单编号")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("所属用户")
    @TableField("owner_user_id")
    private Long ownerUserId;
    @ApiModelProperty("交易号(最终)")
    @TableField("trade_no")
    private String tradeNo;
    @ApiModelProperty("支付方式(最终)")
    @TableField("pay_way")
    private String payWay;
    @ApiModelProperty("订单备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("运费金额")
    @TableField("express_amt")
    private BigDecimal expressAmt;
    @ApiModelProperty("订单销售总额")
    @TableField("total_sale_amt")
    private BigDecimal totalSaleAmt;
    @ApiModelProperty("优惠金额")
    @TableField("discount_amt")
    private BigDecimal discountAmt;
    @ApiModelProperty("调价优惠金额")
    @TableField("adjustment_amt")
    private BigDecimal adjustmentAmt;
    @ApiModelProperty("实际订单总额 = 销售总额 - 优惠金额")
    @TableField("total_real_amt")
    private BigDecimal totalRealAmt;
    @ApiModelProperty("实际付款总额 = 订单总额 + 运费金额")
    @TableField("total_pay_amt")
    private BigDecimal totalPayAmt;

    @ApiModelProperty("交易状态")
    @TableField("trade_status")
    private String tradeStatus;
    @ApiModelProperty("订单状态")
    @TableField("order_status")
    private String orderStatus;
    @ApiModelProperty("支付状态")
    @TableField("pay_status")
    private String payStatus;
    @ApiModelProperty("发货状态")
    @TableField("delivery_status")
    private String deliveryStatus;
    @ApiModelProperty("收货状态")
    @TableField("receive_status")
    private String receiveStatus;
    @ApiModelProperty("售后状态")
    @TableField("refund_status")
    private String refundStatus;

    @ApiModelProperty("计划系统关单时间")
    @TableField("plan_close_at")
    private LocalDateTime planCloseAt;
    @ApiModelProperty("计划系统收货时间")
    @TableField("plan_receive_at")
    private LocalDateTime planReceiveAt;
    @ApiModelProperty("支付时间")
    @TableField("pay_at")
    private LocalDateTime payAt;
    @ApiModelProperty("发货时间")
    @TableField("delivery_at")
    private LocalDateTime deliveryAt;
    @ApiModelProperty("收货时间")
    @TableField("receive_at")
    private LocalDateTime receiveAt;
    @ApiModelProperty("交易完成时间")
    @TableField("finish_at")
    private LocalDateTime finishAt;

    @ApiModelProperty("收货人姓名")
    @TableField("receiver_name")
    private String receiverName;
    @ApiModelProperty("收货人电话")
    @TableField("receiver_tel")
    private String receiverTel;
    @ApiModelProperty("收货人邮编")
    @TableField("receiver_postcode")
    private String receiverPostcode;
    @ApiModelProperty("收货人区域编码")
    @TableField("receiver_adcode")
    private String receiverAdcode;
    @ApiModelProperty("收货人省份/直辖市")
    @TableField("receiver_province")
    private String receiverProvince;
    @ApiModelProperty("收货人城市")
    @TableField("receiver_city")
    private String receiverCity;
    @ApiModelProperty("收货人区")
    @TableField("receiver_region")
    private String receiverRegion;
    @ApiModelProperty("收货人详细地址")
    @TableField("receiver_address")
    private String receiverAddress;


}
