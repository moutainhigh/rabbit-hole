package in.hocg.rabbit.wl.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.wl.biz.entity.Warehouse;
import in.hocg.rabbit.wl.biz.pojo.ro.warehouse.WarehouseCompleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.warehouse.WarehousePagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [物流模块] 仓库表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Mapper
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    IPage<Warehouse> paging(@Param("ro") WarehousePagingRo ro, @Param("ofPage") Page<Object> ofPage);

    IPage<Warehouse> complete(@Param("ro") WarehouseCompleteRo ro, @Param("ofPage") Page ofPage);

    Integer hasWarehouseByCompanyId(@Param("companyId") Long companyId);
}
