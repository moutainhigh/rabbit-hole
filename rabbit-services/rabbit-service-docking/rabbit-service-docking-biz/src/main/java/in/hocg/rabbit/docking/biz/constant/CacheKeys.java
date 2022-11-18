package in.hocg.rabbit.docking.biz.constant;

import in.hocg.boot.cache.autoconfiguration.utils.CacheUtils;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class CacheKeys {
    public enum Prefix {
        WxMpLogin,
        WxMaSession
    }

    public String getWxMaSessionKey(String sessionKey) {
        return CacheUtils.useKey(Prefix.WxMaSession, sessionKey);
    }

    public String getWxMpLoginKey(String randomStr) {
        return CacheUtils.useKey(Prefix.WxMpLogin, randomStr);
    }

}
