package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.mapper.UserGroupMapper;
import com.github.lotus.ums.biz.service.UserGroupService;
import com.github.lotus.ums.biz.service.UserGroupUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserGroup> listByUserId(Long userId) {
        return userGroupUserRefService.listByUserId(userId);
    }
}
