package com.github.lotus.chaos.module.wl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.chaos.module.wl.entity.Warehouse;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehouseCompleteRo;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehouseCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehousePagingRo;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehouseUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.WarehouseComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [物流模块] 仓库表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
public interface WarehouseService extends AbstractService<Warehouse> {

    void create(WarehouseCreateRo ro);

    void update(Long id, WarehouseUpdateRo ro);

    List<WarehouseComplexVo> complete(WarehouseCompleteRo ro);

    IPage<WarehouseComplexVo> paging(WarehousePagingRo ro);

    WarehouseComplexVo getWarehouse(Long id);

    void delete(Long id);

    boolean hasWarehouseByCompanyId(Long companyId);

    List<WarehouseComplexVo> listWarehousesComplexByCompanyId(Long companyId);
}
