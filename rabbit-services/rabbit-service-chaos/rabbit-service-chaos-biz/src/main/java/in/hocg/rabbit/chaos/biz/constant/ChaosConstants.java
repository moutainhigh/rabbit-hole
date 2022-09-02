package in.hocg.rabbit.chaos.biz.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChaosConstants {

    /**
     * 验证码重用的限定时间 2 分钟
     */
    public static final long LIMIT_REUSE_EXPIRED = 2 * 60L;
}
