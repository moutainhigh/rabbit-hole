package in.hocg.rabbit.mall.biz.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [促销模块] 优惠券表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mkt_coupon")
public class Coupon extends LogicDeletedEntity<Coupon> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠券名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("折扣方式：[fixed_amt=>满减；scale_amt=>折扣]")
    @TableField("type")
    private String type;
    @ApiModelProperty("使用说明")
    @TableField("use_instructions")
    private String useInstructions;
    @ApiModelProperty("优惠券备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("满减金额/折扣率")
    @TableField("credit")
    private BigDecimal credit;
    @ApiModelProperty("优惠上限")
    @TableField("ceiling_amt")
    private BigDecimal ceilingAmt;

}
