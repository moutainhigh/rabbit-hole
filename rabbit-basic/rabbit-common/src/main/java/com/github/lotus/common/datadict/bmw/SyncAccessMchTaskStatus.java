package com.github.lotus.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 * init=>初始化; processing=>进行中; done=>完成
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum SyncAccessMchTaskStatus implements DataDictEnum {
    Init("init", "初始化"),
    Processing("processing", "进行中"),
    Success("success", "成功"),
    Fail("fail", "失败"),
    ;
    private final Serializable code;
    private final String name;

}
