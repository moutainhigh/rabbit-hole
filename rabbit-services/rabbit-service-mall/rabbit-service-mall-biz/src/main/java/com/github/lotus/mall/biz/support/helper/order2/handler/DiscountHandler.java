package com.github.lotus.mall.biz.support.helper.order2.handler;



import com.github.lotus.mall.biz.support.helper.order2.discount.Discount;
import com.github.lotus.mall.biz.support.helper.order2.modal.OrderContext;
import com.github.lotus.mall.biz.support.helper.order2.modal.Product;

import java.util.List;

/**
 * Created by hocgin on 2020/7/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface DiscountHandler<D extends Discount, T extends OrderContext, P extends Product> {

    default void handle(D discount, T context, List<P> conformProduct) {

    }

}
