package com.github.lotus.bmw.biz.mapper;

import com.github.lotus.bmw.biz.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付模块] 支付账户表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}