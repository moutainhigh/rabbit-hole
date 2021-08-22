package com.github.lotus.common.datadict.common;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 * 处理状态
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum HandleStatus implements DataDictEnum {
    Init("init", "初始化"),
    Processing("processing", "进行中"),
    Success("success", "成功"),
    Fail("fail", "失败"),
    ;

    private final Serializable code;
    private final String name;
}
