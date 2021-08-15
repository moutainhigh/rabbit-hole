package com.github.lotus.bmw.biz.cache.impl;

import com.github.lotus.bmw.biz.cache.BmwCacheService;
import com.github.lotus.bmw.biz.entity.AccessMch;
import com.github.lotus.bmw.biz.service.AccessMchService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BmwCacheServiceImpl implements BmwCacheService {
    private final AccessMchService accessMchService;

    @Override
    @Cacheable(cacheNames = "getAccessMchByEncoding", key = "#encoding", unless = "#result != null")
    public AccessMch getAccessMchByEncoding(String encoding) {
        return accessMchService.getByEncoding(encoding).orElse(null);
    }
}
