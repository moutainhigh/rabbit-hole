package in.hocg.rabbit.ums.biz.cache;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import in.hocg.boot.cache.autoconfiguration.repository.CacheRepository;
import in.hocg.rabbit.common.utils.JwtUtils;
import in.hocg.rabbit.ums.biz.constant.CacheKeys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UmsCacheService implements UserTokenService {
    private final StringRedisTemplate template;
    private final CacheRepository cacheRepository;


    /**
     * 初始化 qrcode 值
     *
     * @param idFlag qrcode
     */
    public void applyQrcodeLoginKey(@NonNull String idFlag) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheKeys.getQrcodeIdFlag(idFlag);
        opsForValue.set(key, "", 1, TimeUnit.MINUTES);
        log.debug("初始化微信扫码登陆的KEY: [{}]", key);
    }

    /**
     * 根据 qrcode 获取 username
     *
     * @param idFlag qrcode
     * @return username
     */
    public String getQrcodeLoginKey(@NonNull String idFlag) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheKeys.getQrcodeIdFlag(idFlag);
        return opsForValue.get(key);
    }

    /**
     * 设置 qrcode 的 username
     *
     * @param idFlag   qrcode
     * @param username username
     */
    public void updateQrcodeLoginKey(@NonNull String idFlag, @NonNull String username) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheKeys.getQrcodeIdFlag(idFlag);
        Assert.isTrue(template.hasKey(key), "二维码已失效");
        opsForValue.set(key, username);
    }

    @Override
    public String getUsername(String token) {
        String key = CacheKeys.getUserTokenKey(token);
        String username = cacheRepository.get(key);
        if (StrUtil.isBlank(username)) {
            return null;
        }
        cacheRepository.setExpire(key, username, Duration.ofDays(31));
        return username;
    }

    @Override
    public String renewUserToken(String token) {
        String username = JwtUtils.decodeNoExpired(token);
        this.removeUserToken(token);
        return getUserToken(username);
    }

    @Override
    public String getUserToken(String username) {
        String token = JwtUtils.encode(username);
        String key = CacheKeys.getUserTokenKey(token);
        cacheRepository.setExpire(key, username, Duration.ofDays(31));
        return token;
    }

    @Override
    public void removeUserToken(String token) {
        cacheRepository.del(CacheKeys.getUserTokenKey(token));
    }
}
