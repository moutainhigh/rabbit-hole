package com.github.lotus.chaos.module.wl.mapper;

import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.entity.StartingPointRef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [物流模块] 物流起点表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-14
 */
@Mapper
public interface StartingPointRefMapper extends BaseMapper<StartingPointRef> {

    List<LogisticsLine> listLogisticsLineByWarehouseId(@Param("warehouseId") Long warehouseId);
}
