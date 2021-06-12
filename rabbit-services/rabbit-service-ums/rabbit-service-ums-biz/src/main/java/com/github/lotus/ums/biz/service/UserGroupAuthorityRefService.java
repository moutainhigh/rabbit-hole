package com.github.lotus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.entity.UserGroupAuthorityRef;
import com.github.lotus.ums.biz.pojo.ro.UserGroupRefUserPagingRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 用户组x权限表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface UserGroupAuthorityRefService extends AbstractService<UserGroupAuthorityRef> {

    void grantAuthority(Long userGroupId, Long authorityId);

    boolean hasUserGroupByAuthorityId(Long authorityId);

    void grantAuthorities(Long userGroupId, List<Long> authorities);

    IPage<User> pagingUser(UserGroupRefUserPagingRo ro);
}
