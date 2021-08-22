package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.biz.entity.UserAddress;
import com.github.lotus.ums.biz.pojo.vo.UserAddressComplexVo;
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
