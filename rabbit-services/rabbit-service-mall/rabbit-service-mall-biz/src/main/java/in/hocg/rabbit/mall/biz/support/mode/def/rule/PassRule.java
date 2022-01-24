package in.hocg.rabbit.mall.biz.support.mode.def.rule;

import in.hocg.rabbit.mall.biz.support.mode.OrderContext;
import in.hocg.rabbit.mall.biz.support.mode.Rule;
import in.hocg.rabbit.mall.biz.support.mode.RuleResult;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PassRule<T extends OrderContext> extends Rule<T> {

    public PassRule(Serializable id) {
        super(id);
    }

    @Override
    public RuleResult matched(T context) {
        return success(context.getOrderItem());
    }
}
