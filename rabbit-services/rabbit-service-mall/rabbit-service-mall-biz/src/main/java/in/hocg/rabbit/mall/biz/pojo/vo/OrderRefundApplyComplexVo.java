package in.hocg.rabbit.mall.biz.pojo.vo;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
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
    @ChaosNamed(idFor = "handlerId", type = ChaosNamedType.Userid2Nickname)
    private String handlerName;
    @ApiModelProperty("处理时间")
    private LocalDateTime handleAt;

    @ApiModelProperty("收货人")
    private Long receiverId;
    @ChaosNamed(idFor = "receiverId", type = ChaosNamedType.Userid2Nickname)
    private String receiverName;
    @ApiModelProperty("收货时间")
    private LocalDateTime receiveAt;

    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    private LocalDateTime createdAt;
    private Long lastUpdater;
    @ChaosNamed(idFor = "lastUpdater", type = ChaosNamedType.Userid2Nickname)
    private String lastUpdaterName;
    private LocalDateTime lastUpdatedAt;
}
