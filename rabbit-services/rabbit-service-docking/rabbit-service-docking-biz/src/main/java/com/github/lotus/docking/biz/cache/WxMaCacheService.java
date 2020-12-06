package com.github.lotus.docking.biz.cache;

import com.github.lotus.docking.biz.constant.CacheConstant;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMaCacheService {
    private final StringRedisTemplate template;

    public String getWxMaSessionUser(@NonNull String sessionKey) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheConstant.getWxMaSessionKey(sessionKey);
        return opsForValue.get(key);
    }

    public void updateWxMaSessionUser(@NonNull String ssessionKey, @NonNull String username) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheConstant.getWxMaSessionKey(ssessionKey);
        opsForValue.set(key, username);
    }
}
