package in.hocg.rabbit.mall.biz.mapper;

import in.hocg.rabbit.mall.biz.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * <p>
 * [订单模块] 订单商品表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    Optional<OrderItem> getByIdAndOwnerUserId(@Param("id") Long id, @Param("ownerUserId") Long ownerUserId);
}
