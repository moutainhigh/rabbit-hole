package in.hocg.rabbit.bmw.biz.mapper;

import in.hocg.rabbit.bmw.biz.entity.PayRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付模块] 支付记录表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Mapper
public interface PayRecordMapper extends BaseMapper<PayRecord> {

}
