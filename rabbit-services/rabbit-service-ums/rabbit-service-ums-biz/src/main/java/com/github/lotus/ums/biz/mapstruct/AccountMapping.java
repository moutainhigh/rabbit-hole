package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.ums.biz.entity.Account;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountRo;
import com.github.lotus.ums.biz.pojo.vo.AccountComplexVo;
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

    AccountVo asAccountVo(Account entity);

    @Mapping(target = "genderName", ignore = true)
    AccountComplexVo asComplex(Account entity);

    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdIp", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Account asAccount(UpdateAccountRo ro);
}
