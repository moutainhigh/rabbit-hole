package in.hocg.rabbit.wl.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.wl.biz.entity.LogisticsLine;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineBatchCreateRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineCompleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineCreateRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLinePagingRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineSearchRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import in.hocg.rabbit.wl.biz.pojo.vo.LogisticsLineComplexVo;
import in.hocg.rabbit.wl.biz.pojo.vo.LogisticsLineSearchVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

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

    IPage<LogisticsLineSearchVo> search(LogisticsLineSearchRo ro);
}
