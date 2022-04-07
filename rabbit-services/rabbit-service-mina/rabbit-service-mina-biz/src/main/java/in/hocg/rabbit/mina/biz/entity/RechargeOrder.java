package in.hocg.rabbit.mina.biz.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [功能模块] 充值单据表
 * </p>
 *
 * @author hocgin
 * @since 2022-04-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mina_recharge_order")
public class RechargeOrder extends CommonEntity<RechargeOrder> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("外部订单号")
    @TableField("out_order_no")
    private String outOrderNo;
    @ApiModelProperty("内部订单号")
    @TableField("order_no")
    private String orderNo;
    @ApiModelProperty("产品编号")
    @TableField("product_id")
    private String productId;
    @ApiModelProperty("充值账号")
    @TableField("account")
    private String account;
    @ApiModelProperty("通知地址")
    @TableField("notify_url")
    private String notifyUrl;
    @ApiModelProperty("扣费金额")
    @TableField("total_amt")
    private BigDecimal totalAmt;

    @ApiModelProperty("结束通知时间")
    @TableField("finish_notify_at")
    private LocalDateTime finishNotifyAt;
    @ApiModelProperty("下一次通知时间")
    @TableField("next_notify_at")
    private LocalDateTime nextNotifyAt;
    @ApiModelProperty("已通知次数")
    @TableField("notify_recount")
    private Integer notifyRecount;
    @ApiModelProperty("最后一次通知结果")
    @TableField("last_notify_result")
    private String lastNotifyResult;

    @ApiModelProperty("任务状态: [executing=>执行中, success=>成功, failed=>失败]")
    @TableField("status")
    private String status;
    @ApiModelProperty("失败原因")
    @TableField("fail_reason")
    private String failReason;

}
