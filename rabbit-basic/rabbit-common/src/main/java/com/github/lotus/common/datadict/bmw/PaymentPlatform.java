package com.github.lotus.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum PaymentPlatform implements DataDictEnum {
    Unknown("unknown", "未知"),
    AliPay("alipay", "支付宝"),
    WxPay("wxpay", "微信支付");
    private final String code;
    private final String name;
}
