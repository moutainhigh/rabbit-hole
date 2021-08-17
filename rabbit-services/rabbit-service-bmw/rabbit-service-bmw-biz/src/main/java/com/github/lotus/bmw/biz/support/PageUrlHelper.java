package com.github.lotus.bmw.biz.support;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class PageUrlHelper {

    /**
     * 获取收银台地址
     *
     * @param u
     * @return
     */
    public String getCashierUrl(String u) {
        return StrUtil.format("https://bmw.hocgin.top/api/bmw/cashier?u={}", u);
    }

    /**
     * 前往第三方收银台
     *
     * @param key
     * @return
     */
    public static String getGoCashierUrl(String key) {
        return StrUtil.format("https://bmw.hocgin.top/api/bmw/go-cashier?key={}", key);
    }
}
