package com.github.lotus.pay.biz.enumns;

import in.hocg.boot.mybatis.plus.autoconfiguration.constant.DataDictEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2020/6/4.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@ApiModel("支付平台")
@RequiredArgsConstructor
public enum PaymentPlatformType implements DataDictEnum {
    Unknown(0, "未知"),
    AliPay(1, "支付宝"),
    WxPay(2, "微信支付");
    private final Integer code;
    private final String name;
    public static final String KEY = "PaymentPlatform";
}
