package com.github.lotus.docking.biz.cache;

import com.github.lotus.docking.biz.constant.CacheConstant;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMpCacheService {
    private final StringRedisTemplate template;

    public void applyWxLoginKey(@NonNull String sceneStr) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheConstant.getWxMpLoginKey(sceneStr);
        opsForValue.set(key, "", 1, TimeUnit.MINUTES);
        log.debug("初始化微信扫码登陆的KEY: [{}]", key);
    }

    public String getWxLoginKey(@NonNull String sceneStr) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheConstant.getWxMpLoginKey(sceneStr);
        return opsForValue.get(key);
    }

    public void updateWxLoginKey(@NonNull String sceneStr, @NonNull String username) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheConstant.getWxMpLoginKey(sceneStr);
        opsForValue.set(key, username);
    }
}
