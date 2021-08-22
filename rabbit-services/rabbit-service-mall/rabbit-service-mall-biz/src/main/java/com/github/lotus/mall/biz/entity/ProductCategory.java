package com.github.lotus.mall.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * [商品模块] 商品品类表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_product_category")
public class ProductCategory extends TreeEntity<ProductCategory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品品类名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("商品品类描述")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("商品品类图片")
    @TableField("image_url")
    private String imageUrl;
    @ApiModelProperty("排序, 从大到小降序")
    @TableField("priority")
    private Integer priority;

    @TableField("creator")
    private Long creator;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("last_updater")
    private Long lastUpdater;
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;

}