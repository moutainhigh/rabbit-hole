package com.github.lotus.chaos.module.lang.constant;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2020/5/27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class RedisConstants {

    public enum Prefix {
        SMS,
        TOKEN,
        SMS_COUNT
    }

    public String getTokenKey(String username) {
        return prefix(Prefix.TOKEN, username);
    }

    public String getSmsKey(String phone) {
        return prefix(Prefix.SMS, phone);
    }

    public static String getSmsCountKey(String phone) {
        return prefix(Prefix.SMS_COUNT, phone);
    }

    private String prefix(Prefix prefix, String suffix) {
        return String.format("%s::%s", prefix.name(), suffix);
    }
}
