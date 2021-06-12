package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.pojo.ro.SaveUserGroupRo;
import com.github.lotus.ums.biz.pojo.vo.UserGroupComplexVo;
import com.github.lotus.ums.biz.pojo.vo.UserGroupRefUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserGroupMapping {
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserGroupComplexVo asComplex(UserGroup entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserGroup asUserGroup(SaveUserGroupRo ro);

    @Mapping(target = "userId", ignore = true)
    UserGroupRefUserVo asUserGroupRefUserVo(User user);
}
