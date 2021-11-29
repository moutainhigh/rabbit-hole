package in.hocg.rabbit.mina.biz.cache.impl;

import in.hocg.rabbit.mina.biz.cache.MinaCacheService;
import in.hocg.rabbit.mina.biz.constant.CacheConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by hocgin on 2021/11/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MinaCacheServiceImpl implements MinaCacheService {
    private final StringRedisTemplate template;

    @Override
    public void putProxyChannel(String channelId) {
        String key = CacheConstant.getProxyChannelKey(channelId);
        template.opsForValue().set(key, channelId, 5, TimeUnit.MINUTES);
    }

    @Override
    public boolean hasProxyChannel(String channelId) {
        String key = CacheConstant.getProxyChannelKey(channelId);
        return Boolean.TRUE.equals(template.hasKey(key));
    }
}
