package com.github.lotus.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class OrderComplexVo {
    @ApiModelProperty("订单ID")
    private Long id;
    @ApiModelProperty("订单拥有人ID")
    private Long userId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("交易流水号")
    private String tradeNo;

    @ApiModelProperty("运费金额")
    private BigDecimal freightAmt;
    @ApiModelProperty("优惠券抵扣金额")
    private BigDecimal couponDiscountAmt;
    @ApiModelProperty("活动抵扣金额")
    private BigDecimal activityDiscountAmt;
    @ApiModelProperty("后台调整优惠")
    private BigDecimal adjustmentDiscountAmt;
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmt;
    @ApiModelProperty("实际支付金额")
    private BigDecimal userPayAmt;

    @ApiModelProperty("支付方式")
    private Integer payType;

    @ApiModelProperty("订单状态")
    private String orderStatus;

    @ApiModelProperty("确认收货状态")
    private Boolean confirmFlag;

    @ApiModelProperty("收货人姓名")
    private String receiverName;
    @ApiModelProperty("收货人电话")
    private String receiverTel;
    @ApiModelProperty("收货人邮编")
    private String receiverPostcode;
    @ApiModelProperty("收货人区域编码")
    private String receiverAdcode;
    @ApiModelProperty("省份/直辖市")
    private String receiverProvince;
    @ApiModelProperty("城市")
    private String receiverCity;
    @ApiModelProperty("区")
    private String receiverRegion;
    @ApiModelProperty("详细地址")
    private String receiverAddress;
    @ApiModelProperty("订单备注")
    private String remark;
    @ApiModelProperty("支付时间")
    private LocalDateTime payAt;
    @ApiModelProperty("发货时间")
    private LocalDateTime deliveryAt;
    @ApiModelProperty("确认收货时间")
    private LocalDateTime receiveAt;
    @ApiModelProperty("评价时间")
    private LocalDateTime commentedAt;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("订单项")
    private List<OrderItemComplexVo> orderItems = Collections.emptyList();
}
