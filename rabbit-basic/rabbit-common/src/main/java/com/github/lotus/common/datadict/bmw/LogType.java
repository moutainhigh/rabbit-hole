package com.github.lotus.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum LogType implements DataDictEnum {
    Refund("refund", "退款"),
    AsyncNotify("async_notify", "异步通知"),
    SyncNotify("sync_notify", "同步通知"),
    Query("query", "查询"),
    Pay("pay", "支付");
    private final String code;
    private final String name;
}
