package com.github.lotus.chaos.module.wl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineBatchCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineCompleteRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLinePagingRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineSearchRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.LogisticsLineComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [物流模块] 物流线路表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
public interface LogisticsLineService extends AbstractService<LogisticsLine> {

    void update(Long id, LogisticsLineUpdateRo ro);

    void create(LogisticsLineCreateRo ro);

    void delete(Long id);

    LogisticsLineComplexVo getLogisticsLine(Long id);

    IPage<LogisticsLineComplexVo> paging(LogisticsLinePagingRo ro);

    List<LogisticsLineComplexVo> complete(LogisticsLineCompleteRo ro);

    boolean hasLogisticsLineByWarehouseId(Long id);

    List<LogisticsLineComplexVo> listLogisticsLineComplexByWarehouseId(Long warehouseId);

    void batchCreate(LogisticsLineBatchCreateRo ro);

    IPage<LogisticsLineComplexVo> search(LogisticsLineSearchRo ro);
}
