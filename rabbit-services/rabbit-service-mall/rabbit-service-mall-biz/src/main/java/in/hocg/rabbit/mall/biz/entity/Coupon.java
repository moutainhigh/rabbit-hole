package in.hocg.rabbit.mall.biz.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

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
public class Coupon extends AbstractEntity<Coupon> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("优惠券名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("折扣方式：[fixed_amt=>满减；scale_amt=>折扣]")
    @TableField("coupon_type")
    private String couponType;
    @ApiModelProperty("可用类型：[none=>全场通用；category=>指定品类；product=>指定商品]")
    @TableField("use_stint")
    private String useStint;
    @ApiModelProperty("可用平台：[all=>全部；mobile=>移动；pc=>PC]")
    @TableField("use_platform")
    private String usePlatform;
    @ApiModelProperty("优惠券使用说明")
    @TableField("use_instructions")
    private String useInstructions;
    @ApiModelProperty("优惠券备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("订单最低启用金额")
    @TableField("min_point")
    private BigDecimal minPoint;
    @ApiModelProperty("满减金额/折扣率")
    @TableField("credit")
    private BigDecimal credit;
    @ApiModelProperty("优惠上限")
    @TableField("ceiling")
    private BigDecimal ceiling;

    @TableField("creator")
    private Long creator;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("last_updater")
    private Long lastUpdater;
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;



}
