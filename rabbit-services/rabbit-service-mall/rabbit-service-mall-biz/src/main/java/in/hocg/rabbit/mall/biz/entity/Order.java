package in.hocg.rabbit.mall.biz.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

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
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order")
public class Order extends AbstractEntity<Order> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("账号ID")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("订单编号")
    @TableField("order_no")
    private String orderNo;
    @ApiModelProperty("交易流水号")
    @TableField("trade_no")
    private String tradeNo;
    @ApiModelProperty("活动抵扣金额")
    @TableField("activity_discount_amt")
    private BigDecimal activityDiscountAmt;
    @ApiModelProperty("优惠券抵扣金额")
    @TableField("coupon_discount_amt")
    private BigDecimal couponDiscountAmt;
    @ApiModelProperty("管理员后台调整订单使用的折扣金额")
    @TableField("adjustment_discount_amt")
    private BigDecimal adjustmentDiscountAmt;
    @ApiModelProperty("运费金额")
    @TableField("freight_amt")
    private BigDecimal freightAmt;
    @ApiModelProperty("[计算型]订单总金额")
    @TableField("total_amt")
    private BigDecimal totalAmt;
    @ApiModelProperty("[计算型]应付金额（实际支付金额）")
    @TableField("user_pay_amt")
    private BigDecimal userPayAmt;
    @ApiModelProperty("计划关单时间")
    @TableField("plan_close_at")
    private LocalDateTime planCloseAt;
    @ApiModelProperty("计划确认收货时间")
    @TableField("plan_confirm_at")
    private LocalDateTime planConfirmAt;
    @ApiModelProperty("支付方式：[支付宝;微信]")
    @TableField("pay_type")
    private String payType;
    @ApiModelProperty("订单状态：[wait_pay=>待付款；wait_ship=>待发货；wait_receipt=>待收货；received=>已收货；closed=>已关闭;]")
    @TableField("order_status")
    private String orderStatus;
    @ApiModelProperty("是否确认收货")
    @TableField("confirm_flag")
    private Boolean confirmFlag;
    @ApiModelProperty("删除状态 != 0")
    @TableField("delete_flag")
    private Long deleteFlag;
    @ApiModelProperty("订单备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("支付时间")
    @TableField("pay_at")
    private LocalDateTime payAt;
    @ApiModelProperty("发货时间")
    @TableField("delivery_at")
    private LocalDateTime deliveryAt;
    @ApiModelProperty("确认收货时间")
    @TableField("receive_at")
    private LocalDateTime receiveAt;
    @ApiModelProperty("评价时间")
    @TableField("commented_at")
    private LocalDateTime commentedAt;
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

    @TableField("creator")
    private Long creator;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("last_updater")
    private Long lastUpdater;
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;



}
