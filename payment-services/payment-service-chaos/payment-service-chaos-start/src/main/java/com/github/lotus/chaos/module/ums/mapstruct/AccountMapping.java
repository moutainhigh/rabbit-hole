package com.github.lotus.chaos.module.ums.mapstruct;

import com.github.lotus.chaos.module.ums.entity.Account;
import com.github.lotus.chaos.vo.UserDetailVo;
import org.mapstruct.Mapper;

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
