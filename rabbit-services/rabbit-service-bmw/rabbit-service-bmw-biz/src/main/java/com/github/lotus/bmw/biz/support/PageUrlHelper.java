package com.github.lotus.bmw.biz.support;

import cn.hutool.core.util.StrUtil;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.boot.web.autoconfiguration.properties.BootProperties;
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
        String hostname = getHostname();
        return StrUtil.format("{}/cashier?u={}", hostname, u);
    }

    /**
     * 前往第三方收银台
     *
     * @param key
     * @return
     */
    public static String getGoCashierUrl(String key) {
        String hostname = getHostname();
        return StrUtil.format("{}/go-cashier?key={}", hostname, key);
    }

    public static String getHostname() {
        BootProperties properties = SpringContext.getBean(BootProperties.class);
        return StrUtil.format("{}/api/bmw", properties.getHostname());
    }
}
