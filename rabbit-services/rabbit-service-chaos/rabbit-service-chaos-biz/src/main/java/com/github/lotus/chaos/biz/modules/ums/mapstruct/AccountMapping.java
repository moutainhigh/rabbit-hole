package com.github.lotus.chaos.biz.modules.ums.mapstruct;

import com.github.lotus.chaos.api.modules.ums.api.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.api.vo.UserDetailVo;
import com.github.lotus.chaos.biz.modules.ums.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AccountMapping {

    UserDetailVo asUserDetailVo(Account account);

    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "expired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    Account asAccount(CreateAccountRo ro);
}
