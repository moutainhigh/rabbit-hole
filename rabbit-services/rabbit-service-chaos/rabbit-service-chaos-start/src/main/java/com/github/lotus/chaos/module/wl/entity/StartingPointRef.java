package com.github.lotus.chaos.module.wl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * [物流模块] 物流起点表
 * </p>
 *
 * @author hocgin
 * @since 2020-11-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wl_starting_point_ref")
public class StartingPointRef extends AbstractEntity<StartingPointRef> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("物流仓库ID")
    @TableField("warehouse_id")
    private Long warehouseId;
    @ApiModelProperty("物流线路ID")
    @TableField("logistics_line_id")
    private Long logisticsLineId;



}
