package com.github.lotus.ums.biz.enumns;

import in.hocg.boot.mybatis.plus.autoconfiguration.constant.DataDictEnum;
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
public enum Gender implements DataDictEnum {
    Man(1, "男"),
    Woman(0, "女");
    private final Serializable code;
    private final String name;

    public static final String KEY = "gender";
}
