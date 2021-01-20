package com.github.lotus.common.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/12/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class RabbitUtils {

    /**
     * 默认用户名
     * hi_20201011ID
     *
     * @param id
     * @return
     */
    public String getDefaultUsername(Long id) {
        return "hi_" + DateUtil.format(LocalDateTime.now(), DatePattern.PURE_DATE_PATTERN) + id;
    }

    public boolean isSuperAdmin(String username) {
        return false;
    }
}
