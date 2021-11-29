package com.github.lotus.ums.biz.service;

import com.github.lotus.ums.biz.entity.RoleAuthorityRef;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 角色x权限表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface RoleAuthorityRefService extends AbstractService<RoleAuthorityRef> {

    /**
     * 该权限是否有角色在使用
     *
     * @param id 权限
     * @return 是否在使用
     */
    boolean hasRoleByAuthorityId(Long id);

    /**
     * 授权权限给角色
     * - 仅授权指定权限，其他权限会被删除
     *
     * @param roleId      目标角色
     * @param authorities 权限列表
     */
    void grantAuthorities(Long roleId, List<Long> authorities);

    /**
     * 授权某个权限给角色
     *
     * @param roleId      目标角色
     * @param authorityId 权限
     */
    void grantAuthority(Long roleId, Long authorityId);

    /**
     * 获取角色的权限列表
     *
     * @param roleId 角色
     * @return 权限列表
     */
    List<RoleAuthorityRef> listByRoleId(Long roleId);
}
