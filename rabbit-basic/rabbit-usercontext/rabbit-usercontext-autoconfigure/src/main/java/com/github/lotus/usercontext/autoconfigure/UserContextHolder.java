package com.github.lotus.usercontext.autoconfigure;

import com.github.lotus.usercontext.basic.HeaderConstants;
import com.github.lotus.usercontext.ifc.UserContextService;
import com.github.lotus.usercontext.ifc.vo.UserDetail;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.exception.UnAuthenticationException;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.USERNAME));
    }

    public Optional<UserDetail> getUserDetail() {
        return getUsername().map(UserContextHolder::getUserDetailUseCache);
    }

    public Optional<Long> getUserId() {
        return getUserDetail().map(UserDetail::getId);
    }

    public Long getUserIdThrow() {
        return getUserId().orElseThrow(UnAuthenticationException::new);
    }

    public Optional<String> getSource() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.SOURCE));
    }

    public Optional<String> getVersion() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.VERSION));
    }

    @SneakyThrows(ExecutionException.class)
    private UserDetail getUserDetailUseCache(String username) {
        return CACHE_USER.get(username, () -> getUserContextService().getUserDetail(username));
    }

    private HttpServletRequest getRequest() {
        return SpringServletContext.getRequest().orElseThrow(IllegalArgumentException::new);
    }

    private UserContextService getUserContextService() {
        return SpringContext.getBean(UserContextService.class);
    }
}
