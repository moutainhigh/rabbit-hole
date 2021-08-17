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
        return StrUtil.format("https://bmw.hocgin.top/cashier?u={}", u);
    }

    public static String getFormPage(String key) {
        return StrUtil.format("https://bmw.hocgin.top/go-cashier?key={}", key);
    }
}
