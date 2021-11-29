package in.hocg.rabbit.gateway.service.impl;

import in.hocg.rabbit.gateway.constant.CacheKeys;
import in.hocg.rabbit.gateway.service.UserService;
import in.hocg.rabbit.ums.api.AuthorityServiceApi;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(cacheNames = CacheKeys.GET_USER_DETAIL, key = "#username", unless = "#result == null")
    public UserDetailVo getUserDetail(String username) {
        return ValidUtils.notNull(userServiceApi.getUserByUsername(username), "用户不存在");
    }
}
