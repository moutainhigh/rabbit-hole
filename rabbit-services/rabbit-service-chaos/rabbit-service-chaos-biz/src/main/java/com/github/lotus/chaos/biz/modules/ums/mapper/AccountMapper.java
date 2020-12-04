package com.github.lotus.chaos.biz.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.chaos.biz.modules.ums.entity.Account;
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
