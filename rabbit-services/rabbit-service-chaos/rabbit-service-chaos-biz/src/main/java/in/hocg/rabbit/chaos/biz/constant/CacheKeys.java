package in.hocg.rabbit.chaos.biz.constant;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import in.hocg.boot.cache.autoconfiguration.utils.CacheUtils;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public final class CacheKeys {
    public static final String GUMBALLS_GIFT = "GUMBALLS_GIFT";
    public static final String TODAY_WALLPAPER = "TODAY_WALLPAPER";
    public static final String PAGING_WALLPAPER = "PAGING_WALLPAPER";
    public static final String GET_ADDRESS_BY_IP = "GET_ADDRESS_BY_IP";
    public static final String PAGING_TOPIC_WALLPAPER = "PAGING_TOPIC_WALLPAPER";

    public enum Prefix {
        SMS, TOKEN, EMAIL, VerifyCode
    }

    public String getVerifyCodeKey(String optType, String toDevice) {
        return SecureUtil.md5(CacheUtils.useKey(Prefix.VerifyCode, StrUtil.format("{}_{}", optType, toDevice)));
    }


    public String getEmailKey(String email) {
        return CacheUtils.useKey(Prefix.EMAIL, email);
    }

    public String getTokenKey(String username) {
        return CacheUtils.useKey(Prefix.TOKEN, username);
    }

    public String getSmsKey(String phone) {
        return CacheUtils.useKey(Prefix.SMS, phone);
    }


}
