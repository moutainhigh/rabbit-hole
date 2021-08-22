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
public enum CouponUseStint implements DataDictEnum {
    None("none", "不限制"),
    Category("category", "指定品类"),
    Product("product", "指定商品"),
    ;
    private final String code;
    private final String name;
}
