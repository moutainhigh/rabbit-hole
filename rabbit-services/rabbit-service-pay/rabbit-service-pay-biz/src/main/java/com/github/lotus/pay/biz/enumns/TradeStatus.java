package com.github.lotus.pay.biz.enumns;

import in.hocg.boot.utils.enums.DataDictEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2020/5/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@ApiModel("交易状态")
@RequiredArgsConstructor
public enum TradeStatus implements DataDictEnum {
    Init(0, "等待支付"),
    Wait(1, "待付款完成"),
    Success(2, "完成支付"),
    Closed(3, "交易已关闭"),
    Fail(4, "支付失败");
    private final Integer code;
    private final String name;

}
