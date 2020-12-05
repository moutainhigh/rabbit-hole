package com.github.lotus.sso.mapstruct;

import com.github.lotus.chaos.api.modules.ums.pojo.ro.CreateAccountRo;
import com.github.lotus.sso.pojo.ro.JoinRo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AccountMapping {
    @Mapping(target = "createdIp", ignore = true)
    CreateAccountRo asCreateAccountRo(JoinRo ro);
}
