package in.hocg.rabbit.mall.biz.support.mode.def.discount;

import in.hocg.rabbit.mall.biz.support.mode.Discount;
import in.hocg.rabbit.mall.biz.support.mode.OrderContext;
import in.hocg.rabbit.mall.biz.support.mode.Rule;
import in.hocg.rabbit.mall.biz.support.mode.def.rule.Rules;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2022/1/18
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AdjustmentDiscount extends Discount<OrderContext> {
    public AdjustmentDiscount(Serializable id, BigDecimal amt) {
        super(id, List.of(Rules.createPassRule(id)));
    }
}
