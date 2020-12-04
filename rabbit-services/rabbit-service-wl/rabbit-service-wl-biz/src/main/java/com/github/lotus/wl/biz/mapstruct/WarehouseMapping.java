package com.github.lotus.wl.biz.mapstruct;

import com.github.lotus.wl.biz.entity.Warehouse;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseCreateRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseUpdateRo;
import com.github.lotus.wl.biz.pojo.vo.WarehouseComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface WarehouseMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Warehouse asWarehouse(WarehouseCreateRo ro);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Warehouse asWarehouse(WarehouseUpdateRo ro);

    @Mapping(target = "provinceName", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "districtName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "cityName", ignore = true)
    WarehouseComplexVo asWarehouseComplexVo(Warehouse entity);
}
