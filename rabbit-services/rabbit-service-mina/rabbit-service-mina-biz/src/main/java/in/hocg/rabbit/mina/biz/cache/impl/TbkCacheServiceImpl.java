package in.hocg.rabbit.mina.biz.cache.impl;

import in.hocg.boot.cps.autoconfiguration.impl.CpsBervice;
import in.hocg.boot.cps.autoconfiguration.pojo.vo.PrivilegeLinkVo;
import in.hocg.rabbit.mina.biz.cache.TbkCacheService;
import in.hocg.rabbit.mina.biz.constant.CacheKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TbkCacheServiceImpl implements TbkCacheService {
    private final CpsBervice cpsBervice;

    @Override
    @Cacheable(cacheNames = CacheKeys.GET_PRIVILEGE_LINK, key = "#url")
    public String getPrivilegeLink(String url) {
        return cpsBervice.getPrivilegeLink(url).map(PrivilegeLinkVo::getPrivilegeUrl).orElse(null);
    }
}
