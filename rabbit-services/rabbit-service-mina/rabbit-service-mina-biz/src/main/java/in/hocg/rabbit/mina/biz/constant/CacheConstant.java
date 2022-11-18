package in.hocg.rabbit.mina.biz.constant;

import in.hocg.boot.cache.autoconfiguration.utils.CacheUtils;
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
        return CacheUtils.useKey(Prefix.ProxyChannel, token);
    }

}
