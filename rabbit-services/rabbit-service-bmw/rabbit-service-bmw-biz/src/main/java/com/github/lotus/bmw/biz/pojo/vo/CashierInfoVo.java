package com.github.lotus.bmw.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class CashierInfoVo {
    @ApiModelProperty("交易单")
    private String tradeOrderId;
    @ApiModelProperty("交易单号(接入商户)")
    private String outTradeNo;
    @ApiModelProperty("交易单号")
    private String tradeNo;
    @ApiModelProperty("交易金额")
    private BigDecimal tradeAmt;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("计划关单时间")
    private LocalDateTime planCloseAt;
    @ApiModelProperty("交易状态: processing=>进行中; payed=>已支付; cancelled=>已取消; closed=>已关闭")
    private String status;
    @ApiModelProperty("是否担保交易")
    private Boolean guaranteeFlag;
    @ApiModelProperty("订单描述")
    private String orderDesc;
    @ApiModelProperty("关单原因")
    private String reason;
    @ApiModelProperty("支付后前端回跳地址")
    private String frontJumpUrl;

    @ApiModelProperty("接入商户")
    private Long accessMchId;
    private String accessMchName;

    @ApiModelProperty("支付场景")
    private Long paySceneId;

    @ApiModelProperty("可选支付类型")
    private List<PaySceneItemVo> payTypes = Collections.emptyList();
}
