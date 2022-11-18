package in.hocg.rabbit.ums.biz.constant;

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
        UserToken,
        QrcodeIdFlag,
    }

    public String getUserTokenKey(String token) {
        return CacheUtils.useKey(Prefix.UserToken, token);
    }

    public String getQrcodeIdFlag(String idFlag) {
        return CacheUtils.useKey(Prefix.QrcodeIdFlag, idFlag);
    }

}
