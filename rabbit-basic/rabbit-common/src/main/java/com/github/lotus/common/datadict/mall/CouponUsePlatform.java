package com.github.lotus.common.datadict.mall;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum CouponUsePlatform implements DataDictEnum {
    None("none", "不限制"),
    ;
    private final String code;
    private final String name;
}
