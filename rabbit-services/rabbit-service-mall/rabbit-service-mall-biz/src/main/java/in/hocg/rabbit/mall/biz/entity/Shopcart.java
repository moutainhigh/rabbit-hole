package in.hocg.rabbit.mall.biz.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [订单模块] 购物车表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_shopcart")
public class Shopcart extends CommonEntity<Shopcart> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("商品")
    @TableField("product_id")
    private Long productId;
    @ApiModelProperty("加入时，商品单价")
    @TableField("add_unit_price")
    private BigDecimal addUnitPrice;
    @ApiModelProperty("加入时，商品标题")
    @TableField("add_title")
    private String addTitle;
    @ApiModelProperty("加入时，商品图片")
    @TableField("add_image_url")
    private String addImageUrl;
    @ApiModelProperty("SKU ID")
    @TableField("add_sku_id")
    private Long addSkuId;
    @ApiModelProperty("加入的规格属性")
    @TableField("add_sku_spec_data")
    private String addSkuSpecData;
    @ApiModelProperty("加入的数量")
    @TableField("add_quantity")
    private Integer addQuantity;

}
