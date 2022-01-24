package in.hocg.rabbit.mall.biz.mapper;

import in.hocg.rabbit.mall.biz.entity.OrderItemSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [订单模块] 订单详情(商品)表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Mapper
public interface OrderItemSkuMapper extends BaseMapper<OrderItemSku> {

}
