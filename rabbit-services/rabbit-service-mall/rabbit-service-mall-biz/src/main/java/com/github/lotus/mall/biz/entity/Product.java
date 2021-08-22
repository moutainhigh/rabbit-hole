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
public class Product extends AbstractEntity<Product> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("商品品类(t_product_classify)")
    @TableField("product_category_id")
    private Long productCategoryId;
    @ApiModelProperty("产品名")
    @TableField("title")
    private String title;
    @ApiModelProperty("产品属性: {}")
    @TableField("attrs")
    private String attrs;
    @ApiModelProperty("上架状态")
    @TableField("published_flag")
    private Boolean publishedFlag;
    @ApiModelProperty("删除状态: != 0")
    @TableField("delete_flag")
    private Long deleteFlag;
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

    @TableField("creator")
    private Long creator;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("last_updater")
    private Long lastUpdater;
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;



}
