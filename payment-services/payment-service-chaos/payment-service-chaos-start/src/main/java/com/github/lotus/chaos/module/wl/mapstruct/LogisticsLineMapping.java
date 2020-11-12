package com.github.lotus.chaos.module.wl.mapstruct;

import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.entity.Warehouse;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.LogisticsLineComplexVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface LogisticsLineMapping {
    LogisticsLine asLogisticsLine(LogisticsLineUpdateRo ro);

    LogisticsLine asWarehouse(LogisticsLineCreateRo ro);

    LogisticsLineComplexVo asLogisticsLineComplexVo(LogisticsLine entity);
}
