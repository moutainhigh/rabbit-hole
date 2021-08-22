package com.github.lotus.mall.biz.pojo.vo;

import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/3/26.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class OrderRefundApplyComplexVo {
    private Long id;
    @ApiModelProperty("申请编号")
    private String refundApplyNo;
    @ApiModelProperty("申请状态")
    private String applyStatus;
    @ApiModelProperty("订单商品ID")
    private Long orderItemId;
    @ApiModelProperty("退货数量")
    private Integer refundQuantity;
    @ApiModelProperty("退款金额")
    private BigDecimal refundAmt;
    @ApiModelProperty("退货原因")
    private String refundReason;
    @ApiModelProperty("退货备注")
    private String refundRemark;
    @ApiModelProperty("处理备注")
    private String handleRemark;
    @ApiModelProperty("收货备注")
    private String receiveRemark;

    @ApiModelProperty("处理人")
    private Long handlerId;
    @Named(idFor = "handlerId", type = NamedType.Userid2Nickname)
    private String handlerName;
    @ApiModelProperty("处理时间")
    private LocalDateTime handleAt;

    @ApiModelProperty("收货人")
    private Long receiverId;
    @Named(idFor = "receiverId", type = NamedType.Userid2Nickname)
    private String receiverName;
    @ApiModelProperty("收货时间")
    private LocalDateTime receiveAt;

    private Long creator;
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    private LocalDateTime createdAt;
    private Long lastUpdater;
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;
    private LocalDateTime lastUpdatedAt;
}