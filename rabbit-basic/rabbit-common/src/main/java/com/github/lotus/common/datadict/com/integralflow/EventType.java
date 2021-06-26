package com.github.lotus.common.datadict.com.integralflow;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum EventType implements DataDictEnum {
    UserSign("user_sign", "用户签到");
    private final Serializable code;
    private final String name;
}
