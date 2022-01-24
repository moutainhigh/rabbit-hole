package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import in.hocg.rabbit.mall.api.named.MallNamed;
import in.hocg.rabbit.mall.api.named.MallNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/3/26.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel
@InjectNamed
public class OrderMaintainComplexVo {
    @ApiModelProperty("退款申请编号")
    private String encoding;
    @ApiModelProperty("售后类型")
    private String type;
    @MallNamed(idFor = "type", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_MAINTAIN_TYPE})
    private String typeName;

    @ApiModelProperty("申请状态")
    private String status;
    @MallNamed(idFor = "status", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_MAINTAIN_STATUS})
    private String statusName;
    @ApiModelProperty("评价状态")
    private String commentStatus;
    @MallNamed(idFor = "commentStatus", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_MAINTAIN_COMMENT_STATUS})
    private String commentStatusName;

    @ApiModelProperty("退款用户")
    private Long ownerUserId;
    @ChaosNamed(idFor = "ownerUserId", type = ChaosNamedType.Userid2Nickname)
    private String ownerUserName;

    @ApiModelProperty("订单详情")
    private Long orderItemId;
    private OrderItemComplexVo orderItem;

    @ApiModelProperty("退货数量")
    private Integer refundQuantity;
    @ApiModelProperty("退款金额")
    private BigDecimal refundAmt;
    @ApiModelProperty("退货原因")
    private String refundReason;
    @ApiModelProperty("退货备注")
    private String refundRemark;
    @ApiModelProperty("处理人")
    private Long handlerUserId;
    @ChaosNamed(idFor = "handlerUserId", type = ChaosNamedType.Userid2Nickname)
    private String handlerUserName;

    @ApiModelProperty("处理时间")
    private LocalDateTime handleAt;
    @ApiModelProperty("处理备注")
    private String handleRemark;
}
