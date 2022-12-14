package in.hocg.rabbit.ums.biz.mapstruct;

import in.hocg.rabbit.ums.api.pojo.ro.CreateAccountRo;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import in.hocg.rabbit.ums.biz.entity.User;
import in.hocg.rabbit.ums.api.pojo.ro.RegisterRo;
import in.hocg.rabbit.ums.biz.pojo.ro.UpdateAccountRo;
import in.hocg.rabbit.ums.biz.pojo.vo.AccountComplexVo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserCompleteVo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserInfoMeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserMapping {

    UserDetailVo asUserDetailVo(User account);

    @Mapping(target = "avatarUrl", source = "avatarUrl")
    AccountVo asAccountVo(User entity);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "avatar", source = "avatarUrl")
    @Mapping(target = "social", ignore = true)
    @Mapping(target = "genderName", ignore = true)
    AccountComplexVo asComplex(User entity);

    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "expired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdIp", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User asAccount(UpdateAccountRo ro);

    @Mapping(target = "avatar", source = "avatarUrl")
    UserCompleteVo asUserCompleteVo(User entity);

    @Mapping(target = "socials", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdIp", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    CreateAccountRo asCreateAccountRo(RegisterRo ro);

    @Mapping(target = "genderName", ignore = true)
    UserInfoMeVo asUserInfoMeVo(User entity);
}
