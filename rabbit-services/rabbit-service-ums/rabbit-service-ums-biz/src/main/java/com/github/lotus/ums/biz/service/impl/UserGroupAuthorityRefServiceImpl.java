package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.biz.entity.UserGroupAuthorityRef;
import com.github.lotus.ums.biz.mapper.UserGroupAuthorityRefMapper;
import com.github.lotus.ums.biz.service.UserGroupAuthorityRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

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

}
