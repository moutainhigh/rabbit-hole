package in.hocg.rabbit.bmw.biz.support;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2021/7/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class TaskHelper {
    public static final int MAX_REPLAY_COUNT = 12;
    private static final Long[] LEVEL_DELAY = {
        1L,
        3L,
        3L,
        5L,
        5L,
        60L,
        60L,
        60 * 15L,
        60 * 60L,
        2 * 60 * 60L,
        6 * 60 * 60L,
        15 * 60 * 60L,
    };

    /**
     * 获取下一次重试时间
     *
     * @param reCount 当前次数
     * @return
     */
    public static Long getDelaySecond(Integer reCount) {
        return LEVEL_DELAY[Math.min(reCount, LEVEL_DELAY.length - 1)];
    }

}
