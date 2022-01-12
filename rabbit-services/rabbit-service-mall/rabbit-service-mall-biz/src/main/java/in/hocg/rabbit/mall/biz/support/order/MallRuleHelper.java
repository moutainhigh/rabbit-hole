package in.hocg.rabbit.mall.biz.support.order;

import in.hocg.rabbit.mall.biz.support.mode.Rule;
import in.hocg.rabbit.mall.biz.support.mode.def.rule.Rules;
import in.hocg.rabbit.mall.biz.support.order.rule.AvailablePlatformRule;

import java.util.List;

/**
 * Created by hocgin on 2022/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class MallRuleHelper extends Rules {

    /**
     * 可用平台规则
     *
     * @param id                规则
     * @param availablePlatform 可用平台
     * @return 规则
     */
    public static Rule<MallOrderContext> createAvailablePlatformRule(Long id, List<String> availablePlatform) {
        return new AvailablePlatformRule(id, availablePlatform);
    }
}
