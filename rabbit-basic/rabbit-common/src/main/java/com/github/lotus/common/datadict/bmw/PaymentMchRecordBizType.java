package com.github.lotus.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum PaymentMchRecordBizType implements DataDictEnum {
    GoPay("go_pay", "申请支付"),
    GoRefund("go_refund", "申请退款"),
    CloseTrade("close_trade", "关闭交易"),
    ;
    private final Serializable code;
    private final String name;
}
