package com.github.lotus.usercontext.autoconfigure;

import com.github.lotus.usercontext.basic.HeaderConstants;
import com.github.lotus.usercontext.ifc.UserContextService;
import com.github.lotus.usercontext.ifc.vo.UserDetail;
import in.hocg.boot.web.SpringContext;
import in.hocg.boot.web.servlet.SpringServletContext;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

/**
 * Created by hocgin on 2020/8/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class UserContextHolder {
    private final ThreadLocal<Map<String, Object>> cache = ThreadLocal.withInitial(WeakHashMap::new);

    public Optional<String> getUsername() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.USERNAME));
    }

    public Optional<UserDetail> getUserDetail() {
        return getUsername().map(userContextService()::getUserDetail);
    }

    public Optional<Long> getUserId() {
        return getUserDetail().map(UserDetail::getId);
    }

    public Optional<String> getSource() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.SOURCE));
    }

    public Optional<String> getVersion() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.VERSION));
    }

    private HttpServletRequest getRequest() {
        return SpringServletContext.getRequest().orElseThrow(IllegalArgumentException::new);
    }

    private UserContextService userContextService() {
        return SpringContext.getBean(UserContextService.class);
    }
}
