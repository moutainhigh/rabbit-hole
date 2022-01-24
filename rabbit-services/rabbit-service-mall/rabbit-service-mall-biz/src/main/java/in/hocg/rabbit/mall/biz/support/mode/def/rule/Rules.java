package in.hocg.rabbit.mall.biz.support.mode.def.rule;

import in.hocg.rabbit.mall.biz.support.mode.OrderContext;
import in.hocg.rabbit.mall.biz.support.mode.def.DefaultOrderContext;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/1/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Rules {

    /**
     * 全通过规则
     *
     * @param id 规则id
     * @return 规则
     */
    public static <T extends OrderContext> PassRule<T> createPassRule(Serializable id) {
        return new PassRule<>(id);
    }

    /**
     * 启用时间规则
     *
     * @param id       规则id
     * @param enableAt 启用时间
     * @return 规则
     */
    public static <T extends DefaultOrderContext> EnableAtRule<T> createEnableAtRule(Serializable id, LocalDateTime enableAt) {
        return new EnableAtRule<T>(id).setEnabledAt(enableAt);
    }

    /**
     * 到期时间规则
     *
     * @param id        规则id
     * @param expiredAt 到期时间
     * @return 规则
     */
    public static <T extends DefaultOrderContext> ExpiredAtRule<T> createExpiredAtRule(Serializable id, LocalDateTime expiredAt) {
        return new ExpiredAtRule<T>(id).setExpiredTime(expiredAt);
    }
}
