package in.hocg.rabbit.gateway.service.impl;

import in.hocg.boot.utils.ValidUtils;
import in.hocg.rabbit.gateway.constant.CacheKeys;
import in.hocg.rabbit.gateway.service.UserService;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserServiceImpl implements UserService {
    private final UserServiceApi userServiceApi;

    @Override
    @Cacheable(cacheNames = CacheKeys.IS_PASS_AUTHORIZE, key = "#username + #servicePrefix + #methodName + #uri", unless = "#result == null")
    public boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri) {
        return true;
//        todo 如果要开启，需要给每个用户配置接口权限
//        return authorityServiceApi.isPassAuthorize(username, servicePrefix, methodName, uri);
    }

    @Override
    @Cacheable(cacheNames = CacheKeys.GET_USER_DETAIL, key = "#username", unless = "#result == null")
    public UserDetailVo getUserDetail(String username) {
        return ValidUtils.notNull(userServiceApi.getUserByUsername(username), "用户不存在");
    }

    @Override
    @Cacheable(cacheNames = CacheKeys.GET_AUTHORITIES, key = "#username", unless = "#result == null")
    public List<String> getAuthorities(String username) {
        return userServiceApi.getAuthorities(username);
    }

    @Override
    @Cacheable(cacheNames = CacheKeys.GET_USERNAME, key = "#token", unless = "#result == null")
    public String getUsername(String token) {
        return userServiceApi.getUsername(token);
    }
}
