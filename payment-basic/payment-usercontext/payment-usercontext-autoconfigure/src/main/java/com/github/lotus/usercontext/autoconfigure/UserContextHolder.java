package com.github.lotus.usercontext.autoconfigure;

import com.github.lotus.usercontext.basic.HeaderConstants;
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

    /**
     * 获取用户名
     *
     * @return
     */
    public Optional<String> getUsername() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.USERNAME));
    }

    /**
     * 获取请求来源
     *
     * @return
     */
    public Optional<String> getSource() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.SOURCE));
    }

    private HttpServletRequest getRequest() {
        return SpringServletContext.getRequest().orElseThrow(IllegalArgumentException::new);
    }
}
