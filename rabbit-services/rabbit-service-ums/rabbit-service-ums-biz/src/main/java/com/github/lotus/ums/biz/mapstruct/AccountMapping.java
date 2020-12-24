package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.ums.biz.entity.Account;
import com.github.lotus.ums.biz.pojo.vo.AccountComplexVo;
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

    AccountVo asAccountVo(Account entity);

    AccountComplexVo asComplex(Account entity);
}
