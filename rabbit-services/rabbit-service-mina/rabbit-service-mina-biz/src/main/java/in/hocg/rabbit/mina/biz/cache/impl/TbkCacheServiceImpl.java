package in.hocg.rabbit.mina.biz.cache.impl;

import in.hocg.rabbit.mina.biz.cache.TbkCacheService;
import in.hocg.rabbit.mina.biz.constant.CacheKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TbkCacheServiceImpl implements TbkCacheService {

    @Override
    @Cacheable(cacheNames = CacheKeys.GET_PRIVILEGE_LINK, key = "#url", unless = "#result == null")
    public String getPrivilegeLink(String url) {
        // todo
        return null;
    }
}
