package in.hocg.rabbit.mall.biz.support.mode.def.rule;

import in.hocg.rabbit.mall.biz.support.mode.RuleResult;
import in.hocg.rabbit.mall.biz.support.mode.def.DefaultOrderContext;
import in.hocg.rabbit.mall.biz.support.mode.def.DefaultRule;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/1/18
 * email: hocgin@gmail.com
 * 启用时间
 *
 * @author hocgin
 */
@Accessors(chain = true)
public class EnableAtRule<T extends DefaultOrderContext> extends DefaultRule<T> {
    @Getter
    @Setter
    private LocalDateTime enabledAt;

    public EnableAtRule(Serializable id) {
        super(id);
    }

    @Override
    public RuleResult matched(T context) {
        boolean isOk = context.getCreatedAt().isAfter(enabledAt);
        if (isOk) {
            return success(context.getOrderItem());
        }
        return fail("优惠未启用");
    }
}
