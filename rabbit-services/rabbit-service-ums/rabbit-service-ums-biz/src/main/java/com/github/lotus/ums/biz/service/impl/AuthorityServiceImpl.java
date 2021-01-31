package com.github.lotus.ums.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.common.utils.RabbitUtils;
import com.github.lotus.ums.biz.entity.Api;
import com.github.lotus.ums.biz.entity.Authority;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.helper.AuthorityHelper;
import com.github.lotus.ums.biz.mapper.AuthorityMapper;
import com.github.lotus.ums.biz.mapstruct.AuthorityMapping;
import com.github.lotus.ums.biz.pojo.ro.GetAuthorityUserPagingRo;
import com.github.lotus.ums.biz.pojo.ro.GrantRoleRo;
import com.github.lotus.ums.biz.pojo.ro.GrantUserGroupRo;
import com.github.lotus.ums.biz.pojo.ro.SaveAuthorityRo;
import com.github.lotus.ums.biz.pojo.vo.AuthorityComplexVo;
import com.github.lotus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import com.github.lotus.ums.biz.pojo.vo.UserRoleComplexVo;
import com.github.lotus.ums.biz.service.ApiService;
import com.github.lotus.ums.biz.service.AuthorityApiRefService;
import com.github.lotus.ums.biz.service.AuthorityService;
import com.github.lotus.ums.biz.service.RoleAuthorityRefService;
import com.github.lotus.ums.biz.service.UserGroupAuthorityRefService;
import com.github.lotus.ums.biz.service.UserGroupService;
import com.github.lotus.ums.biz.service.UserGroupUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.datastruct.tree.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * <p>
 * [权限模块] 权限表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthorityServiceImpl extends TreeServiceImpl<AuthorityMapper, Authority>
    implements AuthorityService {
    private final AuthorityMapping mapping;
    private final ApiService apiService;
    private final AuthorityApiRefService authorityApiRefService;
    private final RoleAuthorityRefService roleAuthorityRefService;
    private final UserGroupService userGroupService;
    private final UserGroupUserRefService userGroupUserRefService;
    private final UserGroupAuthorityRefService userGroupAuthorityRefService;

    @Override
    public AuthorityComplexVo getAuthority(Long id) {
        return convert(getById(id));
    }

    private AuthorityComplexVo convert(Authority entity) {
        return mapping.asComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(SaveAuthorityRo ro) {
        Long createUser = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Authority entity = mapping.asAuthority(ro);
        entity.setCreator(createUser);
        entity.setCreatedAt(now);
        validInsert(entity);

        Long id = entity.getId();
        List<Long> apis = ro.getApis();
        if (Objects.nonNull(apis)) {
            authorityApiRefService.grantApis(id, apis);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, SaveAuthorityRo ro) {
        Long createUser = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Authority update = mapping.asAuthority(ro);
        update.setId(id);
        update.setLastUpdater(createUser);
        update.setLastUpdatedAt(now);
        validUpdateById(update);

        List<Long> apis = ro.getApis();
        if (Objects.nonNull(apis)) {
            authorityApiRefService.grantApis(id, apis);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        ValidUtils.isFalse(userGroupAuthorityRefService.hasUserGroupByAuthorityId(id), "该权限有用户组正在使用");
        ValidUtils.isFalse(roleAuthorityRefService.hasRoleByAuthorityId(id), "该权限有角色正在使用");
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AuthorityTreeNodeVo> listAuthorityTree(Boolean enabled) {
        List<Authority> data = this.listAuthorityByEnabled(enabled);
        return Tree.getChild(null, LangUtils.toList(data, this::convertTreeNode));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<UserRoleComplexVo> pagingUserByAuthorityId(Long authorityId, GetAuthorityUserPagingRo ro) {
        IPage<User> result = baseMapper.pagingUserByAuthorityId(authorityId, ro, ro.ofPage());
        return result.convert(mapping::asUserRoleComplexVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generateSql() {
        String deleteAll = "delete from ams_authority where 1 = 1;";
        String insertSql = "";
        String checkSql = "delete from ams_role_authority where authority_id not in (select aa.id from ams_authority aa);";
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(deleteAll);
        stringJoiner.add(insertSql);
        stringJoiner.add(checkSql);
        return stringJoiner.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri) {
        List<Api> apis = apiService.listByUsername(username);
        return AuthorityHelper.isPassAuthority(servicePrefix, methodName, uri, apis);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantRole(Long authorityId, GrantRoleRo ro) {
        List<Long> roles = ro.getRoles();
        roles.forEach(roleId -> roleAuthorityRefService.grantAuthority(roleId, authorityId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantUserGroup(Long authorityId, GrantUserGroupRo ro) {
        List<Long> userGroups = ro.getUserGroup();
        userGroups.forEach(userGroupId -> userGroupAuthorityRefService.grantAuthority(userGroupId, authorityId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AuthorityTreeNodeVo> listByProjectIdAndUserId(Long projectId, Long userId) {
        List<Authority> authorities = listAuthoritiesByProjectIdAndUserId(projectId, userId);
        return Tree.getChild(null, LangUtils.toList(authorities, this::convertTreeNode));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> listAuthorityCodeByProjectIdAndUserId(Long projectId, Long userId) {
        List<Authority> authorities = listAuthoritiesByProjectIdAndUserId(projectId, userId);
        return LangUtils.toList(authorities, Authority::getEncoding);
    }

    private List<Authority> listAuthoritiesByProjectIdAndUserId(Long projectId, Long userId) {
        List<Authority> authorities;
        if (RabbitUtils.isSuperAdmin(userId)) {
            authorities = this.listByProjectId(projectId, true);
        } else {
            authorities = baseMapper.listByProjectIdAndUserId(projectId, userId);
        }
        return authorities;
    }

    private List<Authority> listByProjectId(Long projectId, Boolean enabled) {
        return lambdaQuery().eq(Objects.nonNull(projectId), Authority::getProjectId, projectId)
            .eq(Objects.nonNull(enabled), TreeEntity::getEnabled, enabled)
            .orderByAsc(Authority::getPriority).list();
    }

    private AuthorityTreeNodeVo convertTreeNode(Authority entity) {
        AuthorityTreeNodeVo result = new AuthorityTreeNodeVo();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        result.setParentId(entity.getParentId());
        result.setAuthorityCode(entity.getEncoding());
        return result;
    }

    private List<Authority> listAuthorityByEnabled(Boolean enabled) {
        return lambdaQuery().eq(Objects.nonNull(enabled), Authority::getEnabled, enabled).list();
    }
}
