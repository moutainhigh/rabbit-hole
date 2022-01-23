package in.hocg.rabbit.mall.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderMaintainPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [订单模块] 退货申请 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Mapper
public interface OrderMaintainMapper extends BaseMapper<OrderMaintain> {

    IPage<OrderMaintain> paging(@Param("ro") OrderMaintainPagingRo ro, Page<Object> ofPage);
}
