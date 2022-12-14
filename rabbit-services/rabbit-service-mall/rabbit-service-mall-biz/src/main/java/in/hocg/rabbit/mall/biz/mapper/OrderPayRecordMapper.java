package in.hocg.rabbit.mall.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.mall.biz.entity.OrderPayRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [订单模块] 订单支付记录表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Mapper
public interface OrderPayRecordMapper extends BaseMapper<OrderPayRecord> {

}
