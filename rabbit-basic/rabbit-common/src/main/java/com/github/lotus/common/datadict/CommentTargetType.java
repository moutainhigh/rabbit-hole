package com.github.lotus.common.datadict;

import com.github.lotus.common.constant.DistrictLevelConstant;
import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum CommentTargetType implements DataDictEnum {
    Country(DistrictLevelConstant.COUNTRY_CODE, "国家");
    private final Serializable code;
    private final String name;
}
