package in.hocg.rabbit.com.biz.mapper;

import in.hocg.rabbit.com.biz.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [用户模块] 物流地址表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddress> {

}
