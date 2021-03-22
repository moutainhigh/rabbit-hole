package com.github.lotus.pay.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.chaos.api.NamedType;
import com.github.lotus.common.datadict.pay.RefundStatus;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class RefundRecordOrdinaryVo {
    private Long id;
    @ApiModelProperty("交易账单")
    private Long tradeId;
    @ApiModelProperty("退款单号(接入方)")
    private String outRefundSn;
    @ApiModelProperty("退款单号(网关)")
    private String refundSn;
    @ApiModelProperty("退款单号(第三方)")
    private String refundTradeNo;
    @ApiModelProperty("申请退款金额")
    private BigDecimal refundFee;
    @ApiModelProperty("退款理由")
    private String refundReason;
    @ApiModelProperty("退款状态")
    private String refundStatus;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "refundStatus", type = NamedType.DataDict, args = {RefundStatus.Key})
    private String refundStatusName;


    @ApiModelProperty("通知接入应用的地址")
    private String notifyUrl;
    @ApiModelProperty("实际退款金额")
    private BigDecimal settlementRefundFee;
    @ApiModelProperty("最终第三方退款成功的时间(第三方回调填充)")
    private LocalDateTime refundAt;
    @ApiModelProperty("接收到第三方支付通知的时间")
    private LocalDateTime notifyAt;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建ip")
    private String createdIp;
    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
    @ApiModelProperty("更新ip")
    private String updateIp;
}
