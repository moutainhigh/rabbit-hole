package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.biz.entity.RoleUserRef;
import com.github.lotus.ums.biz.mapper.RoleUserRefMapper;
import com.github.lotus.ums.biz.service.RoleUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [权限模块] 角色x账号表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RoleUserRefServiceImpl extends AbstractServiceImpl<RoleUserRefMapper, RoleUserRef> implements RoleUserRefService {

    @Override
    public boolean hasUserByRoleId(Long roleId) {
        return has(RoleUserRef::getRoleId, roleId, RoleUserRef::getId);
    }

    @Override
    public Integer countUseByRoleId(Long id) {
        return lambdaQuery().eq(RoleUserRef::getRoleId, id).count();
    }
}
