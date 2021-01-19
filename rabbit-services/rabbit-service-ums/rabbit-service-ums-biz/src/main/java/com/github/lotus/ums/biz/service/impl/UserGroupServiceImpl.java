package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.mapper.UserGroupMapper;
import com.github.lotus.ums.biz.service.UserGroupService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

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

}
