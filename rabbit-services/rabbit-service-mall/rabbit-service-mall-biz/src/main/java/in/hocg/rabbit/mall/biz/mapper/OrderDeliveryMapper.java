package in.hocg.rabbit.mall.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mall.biz.entity.OrderDelivery;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderDeliveryPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryOrdinaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [订单模块] 配送单 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Mapper
public interface OrderDeliveryMapper extends BaseMapper<OrderDelivery> {

    IPage<OrderDelivery> paging(@Param("ro") OrderDeliveryPagingRo ro, Page<Object> ofPage);
}
