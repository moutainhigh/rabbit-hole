package in.hocg.rabbit.mall.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * [促销模块] 用户优惠券表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mkt_user_coupon")
public class UserCoupon extends LogicDeletedEntity<UserCoupon> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("拥有人")
    @TableField("owner_user_id")
    private Long ownerUserId;
    @ApiModelProperty("优惠券")
    @TableField("coupon_id")
    private Long couponId;
    @ApiModelProperty("优惠券编号")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("优惠券状态")
    @TableField("status")
    private String status;

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


}
