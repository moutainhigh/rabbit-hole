package com.github.lotus.sso.mapstruct;

import com.github.lotus.chaos.modules.ums.ro.CreateAccountRo;
import com.github.lotus.sso.pojo.ro.JoinRo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AccountMapping {
    CreateAccountRo asCreateAccountRo(JoinRo ro);
}
