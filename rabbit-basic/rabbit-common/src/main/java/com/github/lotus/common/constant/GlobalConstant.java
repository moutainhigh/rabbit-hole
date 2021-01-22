package com.github.lotus.common.constant;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class GlobalConstant {
    public static final String DEFAULT_FEIGN_BASE_PACKAGE = "com.github.lotus";
    public static final Long SUPPER_ADMIN_USER_ID = 1L;
    public static final List<String> SUPPER_ADMIN_USERNAMES = Lists.newArrayList("hocgin");
}
