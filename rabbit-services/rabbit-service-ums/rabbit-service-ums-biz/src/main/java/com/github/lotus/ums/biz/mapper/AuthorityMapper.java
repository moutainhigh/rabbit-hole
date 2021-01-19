package com.github.lotus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.ums.biz.entity.Account;
import com.github.lotus.ums.biz.entity.Authority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.ums.biz.pojo.ro.GetAuthorityUserPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [权限模块] 权限表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {

    IPage<Account> pagingUserByAuthorityId(@Param("authorityId") Long authorityId, @Param("ro") GetAuthorityUserPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
