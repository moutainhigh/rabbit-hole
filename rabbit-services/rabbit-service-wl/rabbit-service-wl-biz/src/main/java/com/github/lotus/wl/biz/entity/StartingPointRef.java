package com.github.lotus.wl.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
