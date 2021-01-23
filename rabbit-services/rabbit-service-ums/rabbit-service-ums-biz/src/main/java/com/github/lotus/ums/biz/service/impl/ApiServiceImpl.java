package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.biz.entity.Api;
import com.github.lotus.ums.biz.entity.Role;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.mapper.ApiMapper;
import com.github.lotus.ums.biz.mapstruct.ApiMapping;
import com.github.lotus.ums.biz.pojo.ro.SaveApiRo;
import com.github.lotus.ums.biz.service.UserService;
import com.github.lotus.ums.biz.service.ApiService;
import com.github.lotus.ums.biz.service.RoleService;
import com.github.lotus.ums.biz.service.UserGroupService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * [权限模块] 接口表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ApiServiceImpl extends AbstractServiceImpl<ApiMapper, Api> implements ApiService {
    private final ApiMapping mapping;
    private final UserService accountService;
    private final RoleService roleService;
    private final UserGroupService userGroupService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(SaveApiRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Api entity = mapping.asApi(ro);
        entity.setCreatedAt(now);
        entity.setCreator(userId);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, SaveApiRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Api update = mapping.asApi(ro);
        update.setId(id);
        update.setLastUpdater(userId);
        update.setLastUpdatedAt(now);
        validUpdateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        Api api = getById(id);
        ValidUtils.isFalse(api.getIsPersist(), "该接口为保留接口");
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Api> listByUsername(String username) {
        Optional<User> userOpt = accountService.getByUsername(username);
        if (!userOpt.isPresent()) {
            return Collections.emptyList();
        }
        Long userId = userOpt.get().getId();
        List<Role> roles = roleService.listByUserId(userId);
        List<UserGroup> userGroups = userGroupService.listByUserId(userId);

        List<Api> roleApis = baseMapper.listByRoleIds(LangUtils.toList(roles, Role::getId));
        List<Api> userGroupApis = baseMapper.listByUserGroupIds(LangUtils.toList(userGroups, UserGroup::getId));

        List<Api> result = Lists.newArrayList();
        result.addAll(roleApis);
        result.addAll(userGroupApis);
        return result;
    }

    private boolean hasApiTitle(String title, Long... ignoreId) {
        return has(Api::getTitle, title, Api::getId, ignoreId);
    }

    private boolean hasApiEncoding(String encoding, Long... ignoreId) {
        return has(Api::getEncoding, encoding, Api::getId, ignoreId);
    }

    @Override
    public void validEntity(Api entity) {
        Long id = entity.getId();
        String title = entity.getTitle();
        String encoding = entity.getEncoding();
        if (Objects.nonNull(title)) {
            ValidUtils.isFalse(hasApiTitle(title, id), "接口名称已存在");
        }

        if (Objects.nonNull(encoding)) {
            ValidUtils.isFalse(hasApiEncoding(encoding, id), "接口编码已存在");
        }
    }
}
