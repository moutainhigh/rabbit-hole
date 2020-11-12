package com.github.lotus.chaos.module.wl.mapstruct;

import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.LogisticsLineComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface LogisticsLineMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    LogisticsLine asLogisticsLine(LogisticsLineUpdateRo ro);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    LogisticsLine asWarehouse(LogisticsLineCreateRo ro);

    @Mapping(target = "provinceName", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "districtName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "cityName", ignore = true)
    LogisticsLineComplexVo asLogisticsLineComplexVo(LogisticsLine entity);
}
