package in.hocg.rabbit.mall.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [促销模块] 优惠券可用商品品类表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mkt_coupon_product_category_ref")
public class CouponProductCategoryRef extends AbstractEntity<CouponProductCategoryRef> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("优惠券ID")
    @TableField("coupon_id")
    private Long couponId;
    @ApiModelProperty("产品ID")
    @TableField("product_category_id")
    private Long productCategoryId;



}
