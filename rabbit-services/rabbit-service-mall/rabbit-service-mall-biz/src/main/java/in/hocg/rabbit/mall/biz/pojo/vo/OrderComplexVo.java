package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import in.hocg.rabbit.mall.api.named.MallNamed;
import in.hocg.rabbit.mall.api.named.MallNamedType;
import io.swagger.annotations.ApiModel;
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
@ApiModel
@InjectNamed
public class OrderComplexVo {
    @ApiModelProperty("订单ID")
    private Long id;
    @ApiModelProperty("订单拥有人ID")
    private Long ownerUserId;
    @ChaosNamed(idFor = "ownerUserId", type = ChaosNamedType.Userid2Nickname)
    private String ownerUserName;
    @ApiModelProperty("订单编号")
    private String encoding;
    @ApiModelProperty("交易流水号")
    private String tradeNo;

    @ApiModelProperty("运费金额")
    private BigDecimal expressAmt;
    @ApiModelProperty("订单销售总额")
    private BigDecimal totalSaleAmt;
    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmt;
    @ApiModelProperty("实际订单总额 = 销售总额 - 优惠金额")
    private BigDecimal totalRealAmt;
    @ApiModelProperty("实际付款总额 = 订单总额 + 运费金额")
    private BigDecimal totalPayAmt;
    @ApiModelProperty("调价优惠金额")
    private BigDecimal adjustmentAmt;

    @ApiModelProperty("支付方式(最终)")
    private String payWay;

    @ApiModelProperty("交易状态")
    private String tradeStatus;
    @MallNamed(idFor = "tradeStatus", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_TRADE_STATUS})
    private String tradeStatusName;
    @ApiModelProperty("订单状态")
    private String orderStatus;
    @MallNamed(idFor = "orderStatus", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_STATUS})
    private String orderStatusName;
    @ApiModelProperty("支付状态")
    private String payStatus;
    @MallNamed(idFor = "payStatus", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_PAY_STATUS})
    private String payStatusName;
    @ApiModelProperty("发货状态")
    private String deliveryStatus;
    @MallNamed(idFor = "deliveryStatus", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_DELIVERY_STATUS})
    private String deliveryStatusName;
    @ApiModelProperty("收货状态")
    private String receiveStatus;
    @MallNamed(idFor = "receiveStatus", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_RECEIVE_STATUS})
    private String receiveStatusName;
    @ApiModelProperty("退款状态(待发货)")
    private String refundStatus;
    @MallNamed(idFor = "refundStatus", type = MallNamedType.DataDictName, args = {MallDataDictKeys.ORDER_REFUND_STATUS})
    private String refundStatusName;

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
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("订单项")
    private List<OrderItemComplexVo> orderItems = Collections.emptyList();


    @ApiModelProperty("创建者")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
}
