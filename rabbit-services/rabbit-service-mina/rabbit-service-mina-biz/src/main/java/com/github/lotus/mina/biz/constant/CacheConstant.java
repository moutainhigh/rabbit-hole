package com.github.lotus.mina.biz.constant;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class CacheConstant {
    public enum Prefix {
        ProxyChannel,
    }

    public String getProxyChannelKey(String token) {
        return prefix(Prefix.ProxyChannel, token);
    }

    private String prefix(Prefix prefix, String suffix) {
        return String.format("Mina::%s::%s", prefix.name(), suffix);
    }
}
