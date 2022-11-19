package in.hocg.rabbit.docking.biz.cache;

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
public class WxMaCacheService {
    private final CacheRepository repository;

    public void updateWxMaSessionUser(@NonNull String ssessionKey, @NonNull String username) {
        repository.setExpire(CacheKeys.getWxMaSessionKey(ssessionKey), username, Duration.ofDays(1));
    }
}
