package com.github.lotus.ums.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.ums.biz.entity.Authority;
import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.entity.UserGroupAuthorityRef;
import com.github.lotus.ums.biz.mapper.UserGroupAuthorityRefMapper;
import com.github.lotus.ums.biz.service.AuthorityService;
import com.github.lotus.ums.biz.service.UserGroupAuthorityRefService;
import com.github.lotus.ums.biz.service.UserGroupService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * [权限模块] 用户组x权限表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserGroupAuthorityRefServiceImpl extends AbstractServiceImpl<UserGroupAuthorityRefMapper, UserGroupAuthorityRef> implements UserGroupAuthorityRefService {
    private final UserGroupService userGroupService;
    private final AuthorityService authorityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantAuthority(Long userGroupId, Long authorityId) {
        final UserGroup userGroup = userGroupService.getById(userGroupId);
        ValidUtils.notNull(userGroup, "授权失败");
        Authority authority = authorityService.getById(authorityId);
        ValidUtils.notNull(authority, "授权失败");

        // 已经具备权限
        if (hasByUserGroupIdAndAuthorityId(userGroupId, authorityId)) {
            return;
        }

        UserGroupAuthorityRef entity = new UserGroupAuthorityRef()
            .setAuthorityId(authorityId).setUserGroupId(userGroupId);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean hasUserGroupByAuthorityId(Long authorityId) {
        return has(UserGroupAuthorityRef::getAuthorityId, authorityId, UserGroupAuthorityRef::getId);
    }

    private boolean hasByUserGroupIdAndAuthorityId(Long userGroupId, Long authorityId) {
        return !lambdaQuery().eq(UserGroupAuthorityRef::getUserGroupId, userGroupId)
            .eq(UserGroupAuthorityRef::getAuthorityId, authorityId)
            .page(new Page<>(1, 1, false)).getRecords().isEmpty();
    }
}
