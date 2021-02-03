package com.github.lotus.common.datadict.pay;

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
public enum NotifyResult implements DataDictEnum {
    Init("init", "初始化"),
    Success("success", "成功"),
    Timeout("timeout", "关闭"),
    Fail("fail", "失败");
    private final String code;
    private final String name;
}
