package in.hocg.rabbit.mall.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [促销模块] 优惠券-限制规则表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mkt_coupon_stint_rule_ref")
public class CouponStintRuleRef extends AbstractEntity<CouponStintRuleRef> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("规则")
    @TableField("coupon_id")
    private Long couponId;
    @ApiModelProperty("商品")
    @TableField("rule_id")
    private Long ruleId;



}
