package in.hocg.rabbit.ums.biz.constant;

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
        UserToken,
        QrcodeIdFlag,
    }

    public String getUserTokenKey(String token) {
        return prefix(Prefix.UserToken, token);
    }

    public String getQrcodeIdFlag(String idFlag) {
        return prefix(Prefix.QrcodeIdFlag, idFlag);
    }

    private String prefix(Prefix prefix, String suffix) {
        return String.format("Ums::%s::%s", prefix.name(), suffix);
    }
}
