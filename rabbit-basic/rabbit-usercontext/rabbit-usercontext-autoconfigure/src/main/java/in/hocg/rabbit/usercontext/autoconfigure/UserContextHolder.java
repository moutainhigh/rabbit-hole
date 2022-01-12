package in.hocg.rabbit.usercontext.autoconfigure;

import in.hocg.boot.utils.exception.UnAuthenticationException;
import in.hocg.rabbit.usercontext.basic.HeaderConstants;
import in.hocg.rabbit.usercontext.ifc.UserContextService;
import in.hocg.rabbit.usercontext.ifc.vo.UserDetail;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by hocgin on 2020/8/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class UserContextHolder {
    private static final Cache<String, UserDetail> CACHE_USER = CacheBuilder.newBuilder()
        .softValues()
        .maximumSize(10000L)
        .build();

    public Optional<String> getUsername() {
        return getRequest().map(item -> item.getHeader(HeaderConstants.USERNAME));
    }

    public Optional<UserDetail> getUserInfo() {
        return getUsername().map(UserContextHolder::getUserDetailUseCache);
    }

    public UserDetail getUserInfoThrow() {
        return getUserInfo().orElseThrow(UnAuthenticationException::new);
    }

    public Optional<Long> getUserId() {
        return getUserInfo().map(UserDetail::getId);
    }

    public Long getUserIdThrow() {
        return getUserId().orElseThrow(UnAuthenticationException::new);
    }

    public Optional<String> getSource() {
        return getRequest().map(item -> item.getHeader(HeaderConstants.SOURCE));
    }

    public Optional<String> getVersion() {
        return getRequest().map(item -> item.getHeader(HeaderConstants.VERSION));
    }

    private UserDetail getUserDetailUseCache(String username) {
        UserDetail userDetail = CACHE_USER.getIfPresent(username);
        if (Objects.nonNull(userDetail)) {
            return userDetail;
        }
        userDetail = getUserContextService().getUserDetail(username);
        CACHE_USER.put(username, userDetail);
        return userDetail;
    }

    private Optional<HttpServletRequest> getRequest() {
        return SpringServletContext.getRequest();
    }

    private UserContextService getUserContextService() {
        return SpringContext.getBean(UserContextService.class);
    }
}
