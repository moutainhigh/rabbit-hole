package com.github.lotus.common.datadict.mall;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum OrderStatus implements DataDictEnum {
    WaitPay("wait_pay", "待付款"),
    WaitShip("wait_ship", "待发货"),
    WaitReceipt("wait_receipt", "待收货"),
    Received("received", "已收货"),
    Closed("closed", "已关闭"),
    ;
    private final Serializable code;
    private final String name;

}
