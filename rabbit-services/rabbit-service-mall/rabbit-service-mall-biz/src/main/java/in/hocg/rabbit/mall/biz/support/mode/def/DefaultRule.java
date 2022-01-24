package in.hocg.rabbit.mall.biz.support.mode.def;

import in.hocg.rabbit.mall.biz.support.mode.Rule;
import in.hocg.rabbit.mall.biz.support.mode.RuleResult;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/18
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class DefaultRule<T extends DefaultOrderContext> extends Rule<T> {

    public DefaultRule(Serializable id) {
        super(id);
    }
}
