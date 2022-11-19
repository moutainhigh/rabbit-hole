package in.hocg.rabbit.docking.biz.cache;

import cn.hutool.core.util.StrUtil;
import in.hocg.boot.cache.autoconfiguration.repository.CacheRepository;
import in.hocg.rabbit.docking.biz.constant.CacheKeys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;

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
    private final CacheRepository repository;

    public void applyWxLoginKey(@NonNull String sceneStr) {
        String key = CacheKeys.getWxMpLoginKey(sceneStr);
        repository.setExpire(key, "", Duration.ofMinutes(1));
        log.debug("初始化微信扫码登陆的KEY: [{}]", key);
    }

    public String getWxLoginKey(@NonNull String sceneStr) {
        String key = CacheKeys.getWxMpLoginKey(sceneStr);
        String value = repository.get(key);
        if (StrUtil.isNotBlank(value)) {
            repository.del(key);
        }
        return value;
    }

    public void updateWxLoginKey(@NonNull String sceneStr, @NonNull String username) {
        repository.setExpire(CacheKeys.getWxMpLoginKey(sceneStr), username, Duration.ofHours(1));
    }
}
