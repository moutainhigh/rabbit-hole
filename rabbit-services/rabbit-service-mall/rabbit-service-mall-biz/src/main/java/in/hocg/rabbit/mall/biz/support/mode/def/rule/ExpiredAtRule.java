package in.hocg.rabbit.mall.biz.support.mode.def.rule;

import in.hocg.rabbit.mall.biz.support.mode.def.DefaultOrderContext;
import in.hocg.rabbit.mall.biz.support.mode.Rule;
import in.hocg.rabbit.mall.biz.support.mode.RuleResult;
import in.hocg.rabbit.mall.biz.support.mode.def.DefaultRule;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/1/18
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Accessors(chain = true)
public class ExpiredAtRule<T extends DefaultOrderContext> extends DefaultRule<T> {
    @Getter
    @Setter
    private LocalDateTime expiredTime;

    public ExpiredAtRule(Serializable id) {
        super(id);
    }

    @Override
    public RuleResult matched(T context) {
        boolean isOk = context.getCreatedAt().isBefore(expiredTime);
        if (isOk) {
            return success(context.getOrderItem());
        }
        return fail("优惠已过期");
    }
}
