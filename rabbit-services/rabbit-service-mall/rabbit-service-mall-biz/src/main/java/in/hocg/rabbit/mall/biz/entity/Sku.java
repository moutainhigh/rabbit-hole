package in.hocg.rabbit.mall.biz.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [商品模块] 商品SKU表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_sku")
public class Sku extends AbstractEntity<Sku> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("product_id")
    private Long productId;
    @ApiModelProperty("SKU 编码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("单价(如: 12.00)")
    @TableField("unit_price")
    private BigDecimal unitPrice;
    @ApiModelProperty("库存, 默认为0")
    @TableField("stock")
    private Integer stock;
    @ApiModelProperty("销量, 默认为0")
    @TableField("sale")
    private Integer sale;
    @ApiModelProperty("规格属性(JSONArray, 如: [{\"key\":\"颜色\",\"value\":\"银色\"}])")
    @TableField("spec_data")
    private String specData;
    @ApiModelProperty("特色商品图片")
    @TableField("image_url")
    private String imageUrl;



}
