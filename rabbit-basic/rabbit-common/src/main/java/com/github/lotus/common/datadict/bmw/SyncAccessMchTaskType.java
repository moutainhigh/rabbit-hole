package com.github.lotus.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum SyncAccessMchTaskType implements DataDictEnum {
    TradeResult("trade_result", "交易结果"),
    RefundResult("refund_result", "退款结果"),
    ;
    private final Serializable code;
    private final String name;
}
