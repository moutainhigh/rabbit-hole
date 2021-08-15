package com.github.lotus.bmw.biz.constant;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class BmwConstant {
    public static final String PAYMENT_PREFIX = "/gateway";
    /**
     * 回调地址
     */
    public static final String CALLBACK_URI = "/{paymentMchType}/{paymentMchCode}";
}
