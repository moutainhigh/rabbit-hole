package com.github.lotus.chaos.module.wl.mapstruct;

import com.github.lotus.chaos.module.wl.entity.Warehouse;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehouseCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehouseUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.WarehouseComplexVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface WarehouseMapping {
    Warehouse asWarehouse(WarehouseCreateRo ro);

    Warehouse asWarehouse(WarehouseUpdateRo ro);

    WarehouseComplexVo asWarehouseComplexVo(Warehouse entity);
}
