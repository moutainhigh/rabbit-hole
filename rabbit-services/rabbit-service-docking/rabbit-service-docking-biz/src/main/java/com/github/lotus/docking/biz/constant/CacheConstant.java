package com.github.lotus.docking.biz.constant;

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
        WxLogin
    }

    public String getWxLoginKey(String randomStr) {
        return prefix(Prefix.WxLogin, randomStr);
    }

    private String prefix(Prefix prefix, String suffix) {
        return String.format("%s::%s", prefix.name(), suffix);
    }
}
