package com.github.lotus.chaos.biz.modules.com.enums;

import in.hocg.boot.mybatis.plus.autoconfiguration.constant.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum FileRelType implements DataDictEnum {
    Unknown("unknown", "未知");
    private final Serializable code;
    private final String name;
}
