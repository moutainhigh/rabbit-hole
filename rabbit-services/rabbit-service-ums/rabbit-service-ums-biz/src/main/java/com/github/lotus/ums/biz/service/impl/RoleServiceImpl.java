package com.github.lotus.ums.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.ums.biz.entity.Role;
import com.github.lotus.ums.biz.mapper.RoleMapper;
import com.github.lotus.ums.biz.mapstruct.RoleMapping;
import com.github.lotus.ums.biz.pojo.ro.RolePagingRo;
import com.github.lotus.ums.biz.pojo.ro.SaveRoleRo;
import com.github.lotus.ums.biz.pojo.vo.RoleComplexVo;
import com.github.lotus.ums.biz.service.RoleAuthorityRefService;
import com.github.lotus.ums.biz.service.RoleService;
import com.github.lotus.ums.biz.service.RoleUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * [权限模块] 角色表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RoleServiceImpl extends AbstractServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleMapping mapping;
    private final RoleAuthorityRefService roleAuthorityRefService;
    private final RoleUserRefService roleUserRefService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleComplexVo getRole(Long roleId) {
        return this.covertComplex(getById(roleId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(SaveRoleRo ro) {
        Long createUser = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Role entity = mapping.asRole(ro);

        entity.setCreator(createUser);
        entity.setCreatedAt(now);
        validInsert(entity);

        Long id = entity.getId();
        List<Long> authorities = ro.getAuthorities();
        if (Objects.nonNull(authorities)) {
            roleAuthorityRefService.grantAuthorities(id, authorities);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<RoleComplexVo> paging(RolePagingRo ro) {
        IPage<Role> result = baseMapper.paging(ro, ro.ofPage());
        return result.convert(this::covertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, SaveRoleRo ro) {
        Long createUser = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Role update = mapping.asRole(ro);
        update.setId(id);
        update.setLastUpdater(createUser);
        update.setLastUpdatedAt(now);
        validUpdateById(update);

        List<Long> authorities = ro.getAuthorities();
        if (Objects.nonNull(authorities)) {
            roleAuthorityRefService.grantAuthorities(id, authorities);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        ValidUtils.isFalse(roleUserRefService.hasUserByRoleId(id), "该角色有用户正在使用");
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> listByUserId(Long userId) {
        return roleUserRefService.listByUserId(userId);
    }

    private RoleComplexVo covertComplex(Role entity) {
        return mapping.asComplex(entity)
            .setUseUserCount(roleUserRefService.countUseByRoleId(entity.getId()));
    }
}
