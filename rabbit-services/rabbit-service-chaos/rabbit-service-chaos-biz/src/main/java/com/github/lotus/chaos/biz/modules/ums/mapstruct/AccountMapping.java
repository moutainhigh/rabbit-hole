package com.github.lotus.chaos.biz.modules.ums.mapstruct;

import com.github.lotus.chaos.api.modules.ums.pojo.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.pojo.vo.UserDetailVo;
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

}
