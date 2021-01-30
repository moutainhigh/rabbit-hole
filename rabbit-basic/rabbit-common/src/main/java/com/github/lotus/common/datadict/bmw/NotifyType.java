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
public enum NotifyType implements DataDictEnum {
    Pay("pay", "交易事件"),
    Refund("refund", "退款事件");
    private final String code;
    private final String name;
}
