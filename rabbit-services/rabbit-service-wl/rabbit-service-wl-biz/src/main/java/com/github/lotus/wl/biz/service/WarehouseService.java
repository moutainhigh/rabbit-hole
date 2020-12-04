package com.github.lotus.wl.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.wl.biz.entity.Warehouse;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseCompleteRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseCreateRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseDeleteRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehousePagingRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseUpdateRo;
import com.github.lotus.wl.biz.pojo.vo.WarehouseComplexVo;
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

    /**
     * 物流公司是否关联物流仓库
     *
     * @param companyId
     * @return
     */
    boolean hasWarehouseByCompanyId(Long companyId);

    List<WarehouseComplexVo> listWarehousesComplexByCompanyId(Long companyId);

    void delete(WarehouseDeleteRo ro);

    void delete(Long id);

    List<WarehouseComplexVo> listWarehouseComplexByLogisticsLineId(Long logisticsLineId);
}