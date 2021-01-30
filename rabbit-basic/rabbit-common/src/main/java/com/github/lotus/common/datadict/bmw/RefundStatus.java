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
public enum RefundStatus implements DataDictEnum {
    Init("init", "未退款"),
    Pending("pending", "退款处理中"),
    Success("success", "退款成功"),
    Close("close", "退款关闭"),
    Fail("fail", "退款失败");
    private final String code;
    private final String name;
}
