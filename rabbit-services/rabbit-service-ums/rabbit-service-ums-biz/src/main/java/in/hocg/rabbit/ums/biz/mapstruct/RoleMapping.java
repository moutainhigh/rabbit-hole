package in.hocg.rabbit.ums.biz.mapstruct;

import in.hocg.rabbit.ums.biz.entity.Role;
import in.hocg.rabbit.ums.biz.pojo.ro.SaveRoleRo;
import in.hocg.rabbit.ums.biz.pojo.vo.RoleComplexVo;
import in.hocg.rabbit.ums.biz.pojo.vo.RoleOrdinaryVo;
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

    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    RoleOrdinaryVo asOrdinary(Role entity);
}
