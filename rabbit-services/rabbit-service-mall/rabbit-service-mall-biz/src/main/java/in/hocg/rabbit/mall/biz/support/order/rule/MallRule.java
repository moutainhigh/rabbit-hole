package in.hocg.rabbit.mall.biz.support.order.rule;

import in.hocg.rabbit.mall.biz.support.mode.RuleResult;
import in.hocg.rabbit.mall.biz.support.mode.def.DefaultRule;
import in.hocg.rabbit.mall.biz.support.order.MallOrderContext;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class MallRule extends DefaultRule<MallOrderContext> {

    public MallRule(Serializable id) {
        super(id);
    }
}
