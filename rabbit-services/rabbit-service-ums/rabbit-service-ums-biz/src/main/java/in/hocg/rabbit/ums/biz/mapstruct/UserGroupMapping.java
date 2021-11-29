package in.hocg.rabbit.ums.biz.mapstruct;

import in.hocg.rabbit.ums.biz.entity.User;
import in.hocg.rabbit.ums.biz.entity.UserGroup;
import in.hocg.rabbit.ums.biz.pojo.ro.SaveUserGroupRo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserGroupComplexVo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserGroupRefUserVo;
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
