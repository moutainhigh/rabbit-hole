package in.hocg.rabbit.common.constant;

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
    /**
     * 匿名用户
     */
    public static final Long NO_USER_ID = -1L;
    /**
     * 草鸡管理员列表
     */
    public static final List<String> SUPPER_ADMIN_USERNAMES = Lists.newArrayList("super_admin", "hocgin");
    public static final String AUTHOR = "hocgin";
    /**
     * 用户 Token 过期时长(10年)
     */
    public static final Long USER_TOKEN_TIME_EXPIRE_MILLIS = 10 * 365 * 24 * 60 * 60 * 1000L;
    public static final String SERVICE_NAME = "rabbit-chaos";
    /**
     * [FEIGN] 请求防范机制
     */
    public static final String FEIGN_HEADER = "X-Feign=Y";
    /**
     * [FEIGN] 服务API
     */
    public static final String DEFAULT_FEIGN_BASE_PACKAGE = "in.hocg.rabbit";
    /**
     * [支付中心] 接入商户
     */
    public static final String BMW_ACCESS_CODE = "rabbit";

}
