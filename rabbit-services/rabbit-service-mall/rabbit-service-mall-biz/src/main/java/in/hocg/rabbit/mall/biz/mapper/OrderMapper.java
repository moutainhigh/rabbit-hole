package in.hocg.rabbit.mall.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mall.biz.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * <p>
 * [订单模块] 订单主表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    IPage<Order> paging(@Param("ro") OrderPagingRo ro, Page<Object> ofPage);

    Optional<Order> getByOrderItemId(@Param("orderItemId") Long orderItemId);
}
