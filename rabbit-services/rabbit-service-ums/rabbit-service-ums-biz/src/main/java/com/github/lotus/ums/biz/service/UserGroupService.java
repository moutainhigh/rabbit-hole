package com.github.lotus.ums.biz.service;

import com.github.lotus.ums.biz.entity.UserGroup;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 用户组表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface UserGroupService extends AbstractService<UserGroup> {

    List<UserGroup> listByUserId(Long userId);
}
