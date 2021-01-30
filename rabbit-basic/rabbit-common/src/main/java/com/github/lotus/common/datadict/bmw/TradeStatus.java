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
public enum TradeStatus implements DataDictEnum {
    Init("init", "等待支付"),
    Pending("pending", "待付款完成"),
    Success("success", "完成支付"),
    Close("close", "交易已关闭"),
    Fail("fail", "支付失败");
    private final String code;
    private final String name;

}
