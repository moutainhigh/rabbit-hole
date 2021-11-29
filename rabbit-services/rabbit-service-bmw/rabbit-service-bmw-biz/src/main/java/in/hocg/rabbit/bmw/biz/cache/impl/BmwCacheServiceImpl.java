package in.hocg.rabbit.bmw.biz.cache.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import in.hocg.rabbit.bmw.biz.cache.BmwCacheService;
import in.hocg.rabbit.bmw.biz.entity.AccessMch;
import in.hocg.rabbit.bmw.biz.pojo.dto.CashierInfoDto;
import in.hocg.rabbit.bmw.biz.service.AccessMchService;
import in.hocg.rabbit.bmw.biz.support.PageUrlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BmwCacheServiceImpl implements BmwCacheService {
    private final AccessMchService accessMchService;
    private final RedisTemplate redisTemplate;

    @Override
    // @Cacheable(cacheNames = CacheKeys.GET_ACCESS_MCH_BY_ENCODING, key = "#encoding", unless = "#result == null")
    public AccessMch getAccessMchByEncoding(String encoding) {
        return accessMchService.getByEncoding(encoding).orElse(null);
    }

    @Override
    public Optional<CashierInfoDto> getCashierInfo(String u) {
        return Optional.ofNullable((CashierInfoDto) redisTemplate.opsForValue().get(u));
    }

    @Override
    public String setCashierInfo(CashierInfoDto cashierInfo) {
        String str = StrUtil.format("{}:{}:{}", cashierInfo.getAccessMchId(), cashierInfo.getPaySceneId(), cashierInfo.getTradeOrderId());
        String key = MD5.create().digestHex(str);
        redisTemplate.opsForValue().set(key, cashierInfo, 30, TimeUnit.MINUTES);
        return key;
    }

    @Override
    public String setFormPage(String content) {
        String key = MD5.create().digestHex(content);
        redisTemplate.opsForValue().set(key, content, 15, TimeUnit.MINUTES);
        return PageUrlHelper.getGoCashierUrl(key);
    }

    @Override
    public Optional<String> getFormPage(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return Optional.ofNullable(String.valueOf(valueOperations.get(key)));
    }
}
