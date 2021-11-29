package in.hocg.rabbit.ums.biz.constant;

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
        Token,
        QrcodeIdFlag,
    }

    public String getTokenKey(String token) {
        return prefix(Prefix.Token, token);
    }

    public String getQrcodeIdFlag(String idFlag) {
        return prefix(Prefix.QrcodeIdFlag, idFlag);
    }

    private String prefix(Prefix prefix, String suffix) {
        return String.format("Ums::%s::%s", prefix.name(), suffix);
    }
}
