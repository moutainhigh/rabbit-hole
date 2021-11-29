package in.hocg.rabbit.wl.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.wl.biz.entity.LogisticsLine;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineCompleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLinePagingRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineSearchRo;
import in.hocg.rabbit.wl.biz.pojo.vo.LogisticsLineSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [物流模块] 物流线路表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Mapper
public interface LogisticsLineMapper extends BaseMapper<LogisticsLine> {

    IPage<LogisticsLine> complete(@Param("ro") LogisticsLineCompleteRo ro, @Param("ofPage") Page ofPage);

    IPage<LogisticsLine> paging(@Param("ro") LogisticsLinePagingRo ro, @Param("ofPage") Page ofPage);

    IPage<LogisticsLineSearchVo> search(@Param("starPoint") LogisticsLineSearchRo.Point starPoint, @Param("endPoint") LogisticsLineSearchRo.Point endPoint, @Param("ofPage") Page ofPage);
}
