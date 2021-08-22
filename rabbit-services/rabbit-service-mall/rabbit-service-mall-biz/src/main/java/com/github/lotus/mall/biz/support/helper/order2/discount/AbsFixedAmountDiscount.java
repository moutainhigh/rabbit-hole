package com.github.lotus.mall.biz.support.helper.order2.discount;


import com.github.lotus.mall.biz.support.helper.order2.handler.FixedAmountDiscountHandler;
import com.github.lotus.mall.biz.support.helper.order2.modal.OrderContext;
import com.github.lotus.mall.biz.support.helper.order2.modal.Product;
import com.github.lotus.mall.biz.support.helper.order2.rule.Rule;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2020/7/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class AbsFixedAmountDiscount<T extends OrderContext, P extends Product> extends AbsDiscount<T, P> {
    @Getter
    private BigDecimal val;

    public AbsFixedAmountDiscount(BigDecimal val, List<Rule> rules) {
        super(rules, new FixedAmountDiscountHandler());
        this.val = val;
    }

}
