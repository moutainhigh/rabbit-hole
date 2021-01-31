package com.github.lotus.ums.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.mapper.UserGroupMapper;
import com.github.lotus.ums.biz.mapstruct.UserGroupMapping;
import com.github.lotus.ums.biz.pojo.ro.AssignUserGroupRo;
import com.github.lotus.ums.biz.pojo.ro.SaveUserGroupRo;
import com.github.lotus.ums.biz.pojo.ro.UserGroupCompleteRo;
import com.github.lotus.ums.biz.pojo.ro.UserGroupGrantAuthorityRo;
import com.github.lotus.ums.biz.pojo.ro.UserGroupPagingRo;
import com.github.lotus.ums.biz.pojo.vo.UserGroupComplexVo;
import com.github.lotus.ums.biz.service.AuthorityService;
import com.github.lotus.ums.biz.service.UserGroupAuthorityRefService;
import com.github.lotus.ums.biz.service.UserGroupService;
import com.github.lotus.ums.biz.service.UserGroupUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * [权限模块] 用户组表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserGroupServiceImpl extends AbstractServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService {
    private final UserGroupUserRefService userGroupUserRefService;
    private final UserGroupAuthorityRefService userGroupAuthorityRefService;
    private final AuthorityService authorityService;
    private final UserGroupMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserGroup> listByUserId(Long userId) {
        return userGroupUserRefService.listByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserGroupComplexVo getComplex(Long userGroupId) {
        return this.convertComplex(getById(userGroupId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(SaveUserGroupRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        UserGroup entity = mapping.asUserGroup(ro);
        entity.setCreator(userId);
        entity.setCreatedAt(now);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<UserGroupComplexVo> paging(UserGroupPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserGroupComplexVo> complete(UserGroupCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).convert(this::convertComplex).getRecords();
    }

    @Override
    public void grantAuthority(Long userGroupId, UserGroupGrantAuthorityRo ro) {
        final List<Long> authorities = ro.getAuthorities();
        userGroupAuthorityRefService.grantAuthorities(userGroupId, authorities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long userGroupId, SaveUserGroupRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        UserGroup entity = mapping.asUserGroup(ro);
        entity.setId(userGroupId);
        entity.setLastUpdater(userId);
        entity.setLastUpdatedAt(now);
        validUpdateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long userGroupId) {
        ValidUtils.isFalse(userGroupUserRefService.hasUserByUserGroupId(userGroupId), "该用户组有用户正在使用");
        removeById(userGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignUserGroup(Long userGroupId, AssignUserGroupRo ro) {
        UserGroup userGroup = this.getById(userGroupId);
        ValidUtils.notNull(userGroup, "用户组不存在");
        userGroupUserRefService.assignUserGroup(userGroupId, ro.getAssignUser(), ro.getClearUser());
    }

    private UserGroupComplexVo convertComplex(UserGroup entity) {
        Long userGroupId = entity.getId();
        return mapping.asComplex(entity)
            .setAuthorities(authorityService.listOrdinaryByUserGroupId(userGroupId));
    }
}
