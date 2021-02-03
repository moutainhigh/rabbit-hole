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
public enum NotifyStatus implements DataDictEnum {
    Init("init", "初始化"),
    Pending("pending", "进行中"),
    Success("success", "成功"),
    Close("close", "关闭"),
    Fail("fail", "失败");
    private final String code;
    private final String name;
}
