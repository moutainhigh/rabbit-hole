package in.hocg.rabbit.wl.biz.mapper;

import in.hocg.rabbit.wl.biz.entity.LogisticsLine;
import in.hocg.rabbit.wl.biz.entity.StartingPointRef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.wl.biz.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [物流模块] 物流起点表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-14
 */
@Mapper
public interface StartingPointRefMapper extends BaseMapper<StartingPointRef> {

    List<LogisticsLine> listLogisticsLineByWarehouseId(@Param("warehouseId") Long warehouseId);

    List<Warehouse> listWarehouseByLogisticsLineId(@Param("logisticsLineId") Long logisticsLineId);

    Integer hasLogisticsLineByWarehouseId(@Param("warehouseId") Long warehouseId);
}
