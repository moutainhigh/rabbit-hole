package in.hocg.rabbit.mina.biz.service.impl;

import com.google.common.collect.Maps;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mina.biz.cache.TbkCacheService;
import in.hocg.rabbit.mina.biz.pojo.ro.TbkBatchUrlRo;
import in.hocg.rabbit.mina.biz.service.TbkService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TbkServiceImpl implements TbkService {
    private final TbkCacheService cacheService;

    @Override
    public String getPrivilegeLink(String url) {
        return LangUtils.callIfNotNull(cacheService.getPrivilegeLink(url), this::getPrivilegeLink).orElse(null);
    }

    @Override
    public Map<String, String> getBatchPrivilegeLink(TbkBatchUrlRo ro) {
        Map<String, String> result = Maps.newHashMap();
        LangUtils.getOrDefault(ro.getUrls(), new ArrayList<String>()).forEach(s -> result.put(s, getPrivilegeLink(s)));
        return result;
    }
}
