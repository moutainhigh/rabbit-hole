package com.github.lotus.gateway.service.impl;

import com.github.lotus.gateway.service.UserService;
import com.github.lotus.ums.api.AuthorityServiceApi;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
//    @Cacheable(cacheNames = CacheKeys.GET_USER_DETAIL, key = "#username")
    public UserDetailVo getUserDetail(String username) {
        return ValidUtils.notNull(userServiceApi.getUserByUsername(username), "用户不存在");
    }
}
