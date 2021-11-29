package com.github.lotus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.entity.Authority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.ums.biz.pojo.ro.GetAuthorityUserPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询权限包含的用户列表
     *
     * @param authorityId 权限
     * @param ro          搜索条件
     * @param ofPage      分页条件
     * @return 用户列表
     */
    IPage<User> pagingUserByAuthorityId(@Param("authorityId") Long authorityId, @Param("ro") GetAuthorityUserPagingRo ro, @Param("ofPage") Page<Object> ofPage);

    /**
     * 获取用户拥有的用户组和拥有的角色的权限
     *
     * @param projectId 项目
     * @param userId    用户
     * @return 权限列表
     */
    List<Authority> listByProjectIdAndUserId(@Param("projectId") Long projectId, @Param("userId") Long userId);

    /**
     * 查询角色拥有的权限列表
     *
     * @param roleId 角色
     * @return 权限列表
     */
    List<Authority> listByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询用户组拥有的权限列表
     *
     * @param userGroupId 用户组
     * @return 权限列表
     */
    List<Authority> listByUserGroupId(@Param("userGroupId") Long userGroupId);
}
