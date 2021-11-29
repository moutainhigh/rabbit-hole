package in.hocg.rabbit.ums.biz.cache;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.ums.biz.constant.CacheConstant;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

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
public class UmsCacheService implements TokenCacheService {
    private final StringRedisTemplate template;

    @Override
    public void setToken(String username, String token, long expireMillis) {
        final String tokenKey = CacheConstant.getTokenKey(username);
        ValueOperations<String, String> opsForValue = template.opsForValue();
        opsForValue.set(tokenKey, token, expireMillis, TimeUnit.MINUTES);
        log.debug("设置用户Token[Username: {}, Token: {}]", username, token);
    }

    @Override
    public String getToken(String username) {
        final String tokenKey = CacheConstant.getTokenKey(username);
        ValueOperations<String, String> opsForValue = template.opsForValue();
        return opsForValue.get(tokenKey);
    }

    /**
     * 初始化 qrcode 值
     *
     * @param idFlag qrcode
     */
    public void applyQrcodeLoginKey(@NonNull String idFlag) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        String key = CacheConstant.getQrcodeIdFlag(idFlag);
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
        String key = CacheConstant.getQrcodeIdFlag(idFlag);
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
        String key = CacheConstant.getQrcodeIdFlag(idFlag);
        Assert.isTrue(template.hasKey(key), "二维码已失效");
        opsForValue.set(key, username);
    }
}
