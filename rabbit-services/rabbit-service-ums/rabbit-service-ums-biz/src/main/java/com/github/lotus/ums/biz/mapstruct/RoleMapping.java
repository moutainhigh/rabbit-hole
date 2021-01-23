package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.biz.entity.Role;
import com.github.lotus.ums.biz.pojo.ro.SaveRoleRo;
import com.github.lotus.ums.biz.pojo.vo.RoleComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface RoleMapping {

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Role asRole(SaveRoleRo ro);

    @Mapping(target = "useUserCount", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    RoleComplexVo asComplex(Role role);
}
