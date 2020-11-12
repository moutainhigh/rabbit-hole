package com.github.lotus.chaos.module.wl.service;

import com.github.lotus.chaos.module.wl.entity.WarehouseLogisticsLineRef;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [物流模块] 仓库表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
public interface WarehouseLogisticsLineRefService extends AbstractService<WarehouseLogisticsLineRef> {

    boolean hasByWarehouseId(Long warehouseId);
}
