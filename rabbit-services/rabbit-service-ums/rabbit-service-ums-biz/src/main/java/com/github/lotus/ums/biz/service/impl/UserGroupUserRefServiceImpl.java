package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.entity.UserGroupUserRef;
import com.github.lotus.ums.biz.mapper.UserGroupUserRefMapper;
import com.github.lotus.ums.biz.service.UserGroupUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [权限模块] 用户组x用户表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserGroupUserRefServiceImpl extends AbstractServiceImpl<UserGroupUserRefMapper, UserGroupUserRef> implements UserGroupUserRefService {

    @Override
    public List<UserGroup> listByUserId(Long userId) {
        return baseMapper.listByUserId(userId);
    }
}
