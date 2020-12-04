package com.github.lotus.chaos.module.ums.mapper;

import com.github.lotus.chaos.module.ums.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [用户模块] 账号表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
