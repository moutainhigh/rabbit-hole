package com.github.lotus.mall.biz.support.helper.order2.discount.coupon;


import com.github.lotus.mall.biz.support.helper.order2.discount.AbsFixedAmountDiscount;
import com.github.lotus.mall.biz.support.helper.order2.modal.GeneralOrder;
import com.github.lotus.mall.biz.support.helper.order2.modal.GeneralProduct;
import com.github.lotus.mall.biz.support.helper.order2.rule.Rule;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2020/3/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class FixedAmountCoupon extends AbsFixedAmountDiscount<GeneralOrder, GeneralProduct>
        implements Coupon {

    public FixedAmountCoupon(BigDecimal val, List<Rule> rules) {
        super(val, rules);
    }
}
