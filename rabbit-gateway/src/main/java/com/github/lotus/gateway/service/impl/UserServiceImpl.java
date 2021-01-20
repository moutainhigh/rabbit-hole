package com.github.lotus.gateway.service.impl;

import com.github.lotus.gateway.service.UserService;
import com.github.lotus.ums.api.AuthorityServiceApi;
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

    @Override
    public boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri) {
        return authorityServiceApi.isPassAuthorize(username, servicePrefix, methodName, uri);
    }
}
