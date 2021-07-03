package com.github.lotus.gateway.service.impl;

import com.github.lotus.gateway.constant.CacheKeys;
import com.github.lotus.gateway.service.UserService;
import com.github.lotus.ums.api.AuthorityServiceApi;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserServiceImpl implements UserService {
    private final AuthorityServiceApi authorityServiceApi;
    private final UserServiceApi userServiceApi;

    @Override
    public boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri) {
        return authorityServiceApi.isPassAuthorize(username, servicePrefix, methodName, uri);
    }

    @Override
    @Cacheable(cacheNames = CacheKeys.GET_USER_DETAIL, key = "#username")
    public UserDetails getUserDetail(String username) {
        UserDetailVo userDetail = userServiceApi.getUserByUsername(username);
        ValidUtils.notNull(userDetail, "用户不存在");
        return new User(username, userDetail.getPassword(), userDetail.getEnabled(),
            !userDetail.getExpired(), true, !userDetail.getLocked(), Collections.emptyList());
    }
}
