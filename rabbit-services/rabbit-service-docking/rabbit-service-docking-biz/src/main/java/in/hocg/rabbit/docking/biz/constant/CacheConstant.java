package in.hocg.rabbit.docking.biz.constant;

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
        WxMpLogin,
        WxMaSession
    }

    public String getWxMaSessionKey(String sessionKey) {
        return prefix(Prefix.WxMaSession, sessionKey);
    }

    public String getWxMpLoginKey(String randomStr) {
        return prefix(Prefix.WxMpLogin, randomStr);
    }

    private String prefix(Prefix prefix, String suffix) {
        return String.format("%s::%s", prefix.name(), suffix);
    }
}
