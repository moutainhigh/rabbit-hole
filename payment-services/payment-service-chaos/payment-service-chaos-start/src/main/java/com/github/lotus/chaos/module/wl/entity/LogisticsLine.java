package com.github.lotus.chaos.module.wl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * [物流模块] 物流线路表
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wl_logistics_line")
public class LogisticsLine extends AbstractEntity<LogisticsLine> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("单价")
    @TableField("unit_price")
    private BigDecimal unitPrice;
    @ApiModelProperty("单位(元/方)")
    @TableField("unit")
    private String unit;
    @ApiModelProperty("时效(天)")
    @TableField("aging")
    private Integer aging;
    @ApiModelProperty("物流方式")
    @TableField("shipping_methods")
    private String shippingMethods;
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("物流线路")
    @TableField("warehouse_id")
    private Long warehouseId;

    @ApiModelProperty("[终点]省区域编码")
    @TableField("province_adcode")
    private String provinceAdcode;
    @ApiModelProperty("[终点]市区区域编码")
    @TableField("city_adcode")
    private String cityAdcode;
    @ApiModelProperty("[终点]县区域编码")
    @TableField("district_adcode")
    private String districtAdcode;

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
