package com.github.lotus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.entity.UserGroupAuthorityRef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.ums.biz.pojo.ro.UserGroupRefUserPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [权限模块] 用户组x权限表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface UserGroupAuthorityRefMapper extends BaseMapper<UserGroupAuthorityRef> {

    IPage<User> pagingUser(@Param("ro") UserGroupRefUserPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
