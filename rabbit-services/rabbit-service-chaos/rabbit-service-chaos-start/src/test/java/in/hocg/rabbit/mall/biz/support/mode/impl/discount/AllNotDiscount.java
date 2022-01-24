package in.hocg.rabbit.mall.biz.support.mode.impl.discount;

import in.hocg.rabbit.mall.biz.support.mode.Discount;
import in.hocg.rabbit.mall.biz.support.mode.DiscountResult;
import in.hocg.rabbit.mall.biz.support.mode.impl.TestOrderContext;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by hocgin on 2022/1/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AllNotDiscount extends Discount<TestOrderContext> {

    public AllNotDiscount(Serializable id) {
        super(id);
        setDiscountAmt(BigDecimal.ONE);
    }

    @Override
    public DiscountResult handle(TestOrderContext context, DiscountResult result) {
        return super.handle(context, result);
    }
}
