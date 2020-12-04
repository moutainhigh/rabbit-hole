package com.github.lotus.wl.biz.enumns;

import in.hocg.boot.mybatis.plus.autoconfiguration.constant.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/11/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum Unit implements DataDictEnum {
    YuanCube("yuan_cube", "元每立方");
    private final Serializable code;
    private final String name;

    public static final String KEY = "wl_unit";
}

