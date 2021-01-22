package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.pojo.ro.SaveUserGroupRo;
import com.github.lotus.ums.biz.pojo.vo.UserGroupComplexVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserGroupMapping {
    UserGroupComplexVo asComplex(UserGroup entity);

    UserGroup asUserGroup(SaveUserGroupRo ro);
}
