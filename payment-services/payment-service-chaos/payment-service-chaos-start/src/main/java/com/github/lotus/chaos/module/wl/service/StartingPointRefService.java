package com.github.lotus.chaos.module.wl.service;

import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.entity.StartingPointRef;
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

    boolean hasLogisticsLineByWarehouseId(Long warehouseId);

    List<LogisticsLine> listLogisticsLineByWarehouseId(Long warehouseId);
}
