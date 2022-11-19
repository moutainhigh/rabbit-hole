package in.hocg.rabbit.mina.biz.cache.impl;

import in.hocg.boot.cache.autoconfiguration.repository.CacheRepository;
import in.hocg.rabbit.mina.biz.cache.MinaCacheService;
import in.hocg.rabbit.mina.biz.constant.CacheConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * Created by hocgin on 2021/11/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MinaCacheServiceImpl implements MinaCacheService {
    private final CacheRepository repository;

    @Override
    public void putProxyChannel(String channelId) {
        String key = CacheConstant.getProxyChannelKey(channelId);
        repository.setExpire(key, channelId, Duration.ofMinutes(5));
    }

    @Override
    public boolean hasProxyChannel(String channelId) {
        String key = CacheConstant.getProxyChannelKey(channelId);
        return repository.exists(key);
    }
}
