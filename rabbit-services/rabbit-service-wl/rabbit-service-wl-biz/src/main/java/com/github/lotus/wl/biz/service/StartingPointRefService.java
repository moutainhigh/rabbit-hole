package com.github.lotus.wl.biz.service;

import com.github.lotus.wl.biz.entity.LogisticsLine;
import com.github.lotus.wl.biz.entity.StartingPointRef;
import com.github.lotus.wl.biz.entity.Warehouse;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [物流模块] 物流起点表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-14
 */
public interface StartingPointRefService extends AbstractService<StartingPointRef> {

    void validInsertOrUpdateByLogisticsLineId(Long logisticsLineId, List<StartingPointRef> entities);

    /**
     * 仓库是否关联线路
     *
     * @param warehouseId
     * @return
     */
    boolean hasLogisticsLineByWarehouseId(Long warehouseId);

    List<LogisticsLine> listLogisticsLineByWarehouseId(Long warehouseId);

    List<Warehouse> listWarehouseByLogisticsLineId(Long logisticsLineId);
}
