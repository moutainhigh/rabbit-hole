package com.github.lotus.common.datadict.mall;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum UserCouponStatus implements DataDictEnum {
    UnUse("un_use", "未使用"),
    Used("used", "已使用"),
    Cancelled("cancelled", "已取消"),
    ;
    private final Serializable code;
    private final String name;

}
