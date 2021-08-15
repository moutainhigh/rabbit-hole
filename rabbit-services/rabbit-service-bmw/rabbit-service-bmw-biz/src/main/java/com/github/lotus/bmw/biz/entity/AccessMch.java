package com.github.lotus.bmw.biz.entity;

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
 * [支付模块] 接入商户表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_access_mch")
public class AccessMch extends AbstractEntity<AccessMch> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("商户编码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("商户标题")
    @TableField("title")
    private String title;
    @ApiModelProperty("商户配置")
    @TableField("config")
    private String config;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;
    @ApiModelProperty("更新时间")
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    @TableField("last_updater")
    private Long lastUpdater;



}
