package com.github.lotus.mall.biz.support.helper.order2.rule;


import com.github.lotus.mall.biz.support.helper.order2.modal.OrderContext;

/**
 * Created by hocgin on 2020/7/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Rule<T extends OrderContext> {

    CheckResult check(T context);

}
