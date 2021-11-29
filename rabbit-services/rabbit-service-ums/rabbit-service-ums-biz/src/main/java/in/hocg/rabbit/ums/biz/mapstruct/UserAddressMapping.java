package in.hocg.rabbit.ums.biz.mapstruct;

import in.hocg.rabbit.ums.biz.entity.UserAddress;
import in.hocg.rabbit.ums.biz.pojo.vo.UserAddressComplexVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserAddressMapping {

    UserAddressComplexVo asUserAddressComplexVo(UserAddress entity);
}
