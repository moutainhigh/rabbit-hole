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

    boolean hasRoleByAuthorityId(Long id);

    void grantAuthorities(Long roleId, List<Long> authorities);
}
