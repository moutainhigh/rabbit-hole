package com.github.lotus.chaos.biz.modules.com.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2020/8/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@ApiModel("城市规划级别")
@RequiredArgsConstructor
public enum DistrictLevel {
    Country(com.github.lotus.chaos.api.modules.com.constant.DistrictLevel.COUNTRY_CODE, "国家"),
    Province(com.github.lotus.chaos.api.modules.com.constant.DistrictLevel.PROVINCE_CODE, "省份"),
    City(com.github.lotus.chaos.api.modules.com.constant.DistrictLevel.CITY_CODE, "城市"),
    District(com.github.lotus.chaos.api.modules.com.constant.DistrictLevel.DISTRICT_CODE, "区域"),
    Street(com.github.lotus.chaos.api.modules.com.constant.DistrictLevel.STREET_CODE, "街道");
    private final String code;
    private final String name;

    public final static String KEY = "DistrictLevel";
}
