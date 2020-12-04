package com.github.lotus.wl.biz.mapstruct;

import com.github.lotus.wl.biz.entity.LogisticsLine;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLineBatchCreateRo;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLineCreateRo;
import com.github.lotus.wl.biz.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import com.github.lotus.wl.biz.pojo.vo.LogisticsLineComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface LogisticsLineMapping {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    LogisticsLine asLogisticsLine(LogisticsLineUpdateRo ro);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    LogisticsLine asLogisticsLine(LogisticsLineCreateRo ro);

    @Mapping(target = "provinceName", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "districtName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "cityName", ignore = true)
    LogisticsLineComplexVo asLogisticsLineComplexVo(LogisticsLine entity);

    @Mapping(target = "warehouseId", source = "warehouseId")
    @Mapping(target = "creator", source = "creator")
    @Mapping(target = "provinceAdcode", source = "provinceAdcode")
    @Mapping(target = "cityAdcode", source = "cityAdcode")
    @Mapping(target = "unitPrice", source = "logisticsLine.unitPrice")
    @Mapping(target = "unit", source = "logisticsLine.unit")
    @Mapping(target = "aging", source = "logisticsLine.aging")
    @Mapping(target = "shippingMethods", source = "logisticsLine.shippingMethods")
    @Mapping(target = "remark", source = "logisticsLine.remark")
    @Mapping(target = "districtAdcode", source = "logisticsLine.districtAdcode")
    LogisticsLineCreateRo aslogisticsLineCreateRo(LogisticsLineBatchCreateRo.LogisticsLineCreateRo logisticsLine,
                                                  String provinceAdcode,
                                                  String cityAdcode,
                                                  List<Long> warehouseId, Long creator);
}
