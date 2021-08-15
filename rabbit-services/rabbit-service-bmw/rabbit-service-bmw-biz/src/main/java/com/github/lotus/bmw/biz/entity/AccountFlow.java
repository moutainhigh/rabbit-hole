package com.github.lotus.bmw.biz.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * [支付模块] 账户流水表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_account_flow")
public class AccountFlow extends AbstractEntity<AccountFlow> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("业务类型")
    @TableField("ref_type")
    private String refType;
    @ApiModelProperty("业务对象")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("记账类型: refund=>退款; trade=>交易; withdraw=>提现; ledger=>分账")
    @TableField("type")
    private String type;
    @ApiModelProperty("记账方向: in=>入账; out=>出账")
    @TableField("dire")
    private String dire;
    @ApiModelProperty("记账账户")
    @TableField("booking_act_id")
    private Long bookingActId;
    @ApiModelProperty("记账金额")
    @TableField("booking_amt")
    private BigDecimal bookingAmt;
    @ApiModelProperty("记账时间")
    @TableField("booking_at")
    private LocalDateTime bookingAt;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;
    @ApiModelProperty("更新时间")
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    @TableField("last_updater")
    private Long lastUpdater;

}
