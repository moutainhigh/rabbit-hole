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
    public static final String PAYMENT_PREFIX = "/gateway/result";
    /**
     * 回调地址 [支付]
     */
    public static final String PAY_CALLBACK_URI = "/{paymentMchType}/{paymentMchCode}/pay/{payRecordId}";
    /**
     * 回调地址 [退款]
     */
    public static final String REFUND_CALLBACK_URI = "/{paymentMchType}/{paymentMchCode}/refund";
}
