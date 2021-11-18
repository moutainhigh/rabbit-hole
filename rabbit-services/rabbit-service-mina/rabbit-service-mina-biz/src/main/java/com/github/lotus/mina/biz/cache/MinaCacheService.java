package com.github.lotus.mina.biz.cache;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by hocgin on 2021/11/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface MinaCacheService {

    /**
     * 保存隧道标识
     *
     * @param channelId
     */
    void putProxyChannel(String channelId);

    /**
     * 检查是否注册隧道
     *
     * @param channelId
     * @return
     */
    boolean hasProxyChannel(String channelId);
}
