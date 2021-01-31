package com.github.lotus.ums.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.ums.biz.entity.Authority;
import com.github.lotus.ums.biz.entity.Role;
import com.github.lotus.ums.biz.entity.RoleAuthorityRef;
import com.github.lotus.ums.biz.mapper.RoleAuthorityRefMapper;
import com.github.lotus.ums.biz.service.AuthorityService;
import com.github.lotus.ums.biz.service.RoleAuthorityRefService;
import com.github.lotus.ums.biz.service.RoleService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * <p>
 * [权限模块] 角色x权限表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RoleAuthorityRefServiceImpl extends AbstractServiceImpl<RoleAuthorityRefMapper, RoleAuthorityRef> implements RoleAuthorityRefService {
    private final RoleService roleService;
    private final AuthorityService authorityService;

    @Override
    public boolean hasRoleByAuthorityId(Long id) {
        return has(RoleAuthorityRef::getId, id, RoleAuthorityRef::getId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantAuthorities(Long roleId, List<Long> authorities) {
        List<RoleAuthorityRef> entities = authorities.parallelStream()
            .map(authorityId -> new RoleAuthorityRef().setAuthorityId(authorityId).setRoleId(roleId))
            .collect(Collectors.toList());
        List<RoleAuthorityRef> allData = this.listRoleAuthorityByAuthorityId(roleId);

        final BiFunction<RoleAuthorityRef, RoleAuthorityRef, Boolean> isSame =
            (t1, t2) -> LangUtils.equals(t1.getAuthorityId(), t2.getAuthorityId());
        final List<RoleAuthorityRef> mixedList = LangUtils.getMixed(allData, entities, isSame);
        List<RoleAuthorityRef> deleteList = LangUtils.removeIfExits(allData, mixedList, isSame);
        List<RoleAuthorityRef> addList = LangUtils.removeIfExits(entities, mixedList, isSame);

        // 删除
        this.removeByIds(deleteList.parallelStream()
            .map(RoleAuthorityRef::getId)
            .collect(Collectors.toList()));

        // 新增
        addList.forEach(this::validInsertOrUpdate);

        // 更新
        mixedList.forEach(this::validInsertOrUpdate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantAuthority(Long roleId, Long authorityId) {
        final Role role = roleService.getById(roleId);
        ValidUtils.notNull(role, "授权失败");
        Authority authority = authorityService.getById(authorityId);
        ValidUtils.notNull(authority, "授权失败");

        // 已经具备权限
        if (hasByRoleIdAndAuthorityId(roleId, authorityId)) {
            return;
        }

        RoleAuthorityRef entity = new RoleAuthorityRef()
            .setAuthorityId(authorityId).setRoleId(roleId);
        validInsert(entity);
    }

    @Override
    public List<RoleAuthorityRef> listByRoleId(Long roleId) {
        return lambdaQuery().eq(RoleAuthorityRef::getRoleId, roleId).list();
    }

    private boolean hasByRoleIdAndAuthorityId(Long roleId, Long authorityId) {
        return !lambdaQuery().eq(RoleAuthorityRef::getRoleId, roleId)
            .eq(RoleAuthorityRef::getAuthorityId, authorityId)
            .page(new Page<>(1, 1, false)).getRecords().isEmpty();
    }

    private List<RoleAuthorityRef> listRoleAuthorityByAuthorityId(Long roleId) {
        return lambdaQuery().eq(RoleAuthorityRef::getRoleId, roleId).list();
    }
}
