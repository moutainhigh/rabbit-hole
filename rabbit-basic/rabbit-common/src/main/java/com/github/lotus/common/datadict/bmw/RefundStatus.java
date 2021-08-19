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
public enum RefundStatus implements DataDictEnum {
    Processing("processing", "进行中"),
    Success("success", "成功"),
    Fail("fail", "失败");
    private final Serializable code;
    private final String name;
}
