package com.github.lotus.pay.biz.support.payment.resolve.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum FeatureType {
    Refund("refund", "退款"),
    Pay("pay", "支付");
    private final String code;
    private final String name;
}
