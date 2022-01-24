package in.hocg.rabbit.mall.biz.support.mode.impl.discount;

import in.hocg.rabbit.mall.biz.support.mode.Discount;
import in.hocg.rabbit.mall.biz.support.mode.DiscountResult;
import in.hocg.rabbit.mall.biz.support.mode.def.rule.PassRule;
import in.hocg.rabbit.mall.biz.support.mode.impl.TestOrderContext;
import in.hocg.rabbit.mall.biz.support.mode.def.rule.Rules;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2022/1/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AllPassDiscount extends Discount<TestOrderContext> {

    public AllPassDiscount(Serializable id) {
        super(id, List.of(Rules.createPassRule(0L)));
    }

    @Override
    public DiscountResult handle(TestOrderContext context, DiscountResult result) {
        setDiscountAmt(BigDecimal.ONE);
        return super.handle(context, result);
    }
}
