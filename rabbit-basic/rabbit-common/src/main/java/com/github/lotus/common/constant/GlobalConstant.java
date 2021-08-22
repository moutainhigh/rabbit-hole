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
    public static final String AUTHOR = "hocgin";
    /**
     * 用户 Token 过期时长(10年)
     */
    public static final Long USER_TOKEN_TIME_EXPIRE_MILLIS = 10 * 365 * 24 * 60 * 60 * 1000L;
    public static final String SERVICE_NAME = "rabbit-chaos";
    public static final String DEFAULT_FEIGN_BASE_PACKAGE = "com.github.lotus";
    /**
     * 草鸡管理员用户ID
     */
    @Deprecated(/*废弃:用户ID后续不方便维护*/)
    public static final Long SUPPER_ADMIN_USER_ID = 1L;
    /**
     * 草鸡管理员列表
     */
    public static final List<String> SUPPER_ADMIN_USERNAMES = Lists.newArrayList("super_admin", "hocgin");

    public static final String FEIGN_HEADER = "X-Feign=Y";

}
