package in.hocg.rabbit.mall.biz.support.order.rule;

import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.enums.order.OrderedSource;
import in.hocg.rabbit.mall.biz.support.mode.RuleResult;
import in.hocg.rabbit.mall.biz.support.order.MallOrderContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hocgin on 2022/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AvailablePlatformRule extends MallRule {
    private final List<String> availablePlatform;

    public AvailablePlatformRule(Serializable id, List<String> availablePlatform) {
        super(id);
        this.availablePlatform = availablePlatform;
    }

    @Override
    public RuleResult matched(MallOrderContext context) {
        if (availablePlatform.contains(context.getSource().getCodeStr())) {
            return success(context.getOrderItem());
        }
        return fail("仅限{}下单可用", availablePlatform);
    }

    @Getter
    @RequiredArgsConstructor
    public enum RuleUsePlatform implements DataDictEnum {
        None("none", "不限制"),
        Unknown(OrderedSource.Unknown.getCodeStr(), OrderedSource.Unknown.getName()),
        ;
        private final String code;
        private final String name;
    }

}
