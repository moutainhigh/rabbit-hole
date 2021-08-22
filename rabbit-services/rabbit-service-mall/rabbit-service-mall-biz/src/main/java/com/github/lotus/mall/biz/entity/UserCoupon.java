package com.github.lotus.mall.biz.entity;

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
 * [促销模块] 优惠券账号拥有人表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mkt_user_coupon")
public class UserCoupon extends AbstractEntity<UserCoupon> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("拥有人")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("mkt_COUPON ID")
    @TableField("coupon_id")
    private Long couponId;
    @ApiModelProperty("优惠券编号")
    @TableField("coupon_no")
    private String couponNo;
    @ApiModelProperty("已使用状态")
    @TableField("use_flag")
    private Boolean useFlag;
    @ApiModelProperty("过期状态")
    @TableField("expired_flag")
    private Boolean expiredFlag;
    @ApiModelProperty("优惠券生效时间")
    @TableField("start_at")
    private LocalDateTime startAt;
    @ApiModelProperty("优惠券失效时间")
    @TableField("end_at")
    private LocalDateTime endAt;
    @ApiModelProperty("使用时间")
    @TableField("used_at")
    private LocalDateTime usedAt;
    @ApiModelProperty("实际抵扣金额")
    @TableField("used_amt")
    private BigDecimal usedAmt;

    @TableField("creator")
    private Long creator;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("last_updater")
    private Long lastUpdater;
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;



}
