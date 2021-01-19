package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.biz.entity.Account;
import com.github.lotus.ums.biz.entity.Authority;
import com.github.lotus.ums.biz.pojo.ro.SaveAuthorityRo;
import com.github.lotus.ums.biz.pojo.vo.AuthorityComplexVo;
import com.github.lotus.ums.biz.pojo.vo.UserRoleComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AuthorityMapping {

    @Mapping(target = "projectId", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "treePath", ignore = true)
    @Mapping(target = "id", ignore = true)
    Authority asAuthority(SaveAuthorityRo ro);

    AuthorityComplexVo asAuthorityComplexVo(Authority entity);

    @Mapping(target = "userId", source = "id")
    UserRoleComplexVo asUserRoleComplexVo(Account account);
}
