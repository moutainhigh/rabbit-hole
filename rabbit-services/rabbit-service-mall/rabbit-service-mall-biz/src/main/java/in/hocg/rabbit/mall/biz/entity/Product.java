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
 * [商品模块] 商品表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_product")
public class Product extends LogicDeletedEntity<Product> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("店铺")
    @TableField("shop_id")
    private Long shopId;
    @ApiModelProperty("商品品类")
    @TableField("category_id")
    private Long categoryId;
    @ApiModelProperty("产品名")
    @TableField("title")
    private String title;
    @ApiModelProperty("产品属性: []")
    @TableField("attrs")
    private String attrs;
    @ApiModelProperty("上架状态")
    @TableField("published_flag")
    private Boolean publishedFlag;
    @ApiModelProperty("video url")
    @TableField("video_url")
    private String videoUrl;
    @ApiModelProperty("采购地(中国,福建)")
    @TableField("procurement")
    private String procurement;
    @ApiModelProperty("单位")
    @TableField("unit")
    private String unit;
    @ApiModelProperty("商品重量(克)")
    @TableField("weight")
    private BigDecimal weight;


}
