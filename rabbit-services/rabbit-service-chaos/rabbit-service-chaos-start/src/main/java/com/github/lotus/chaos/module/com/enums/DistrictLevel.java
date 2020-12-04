package com.github.lotus.chaos.module.com.enums;

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
    Country(DistrictLevel.COUNTRY_CODE, "国家"),
    Province(DistrictLevel.PROVINCE_CODE, "省份"),
    City(DistrictLevel.CITY_CODE, "城市"),
    District(DistrictLevel.DISTRICT_CODE, "区域"),
    Street(DistrictLevel.STREET_CODE, "街道");
    private final String code;
    private final String name;

    public final static String KEY = "DistrictLevel";

    /**
     * 常量
     **/
    public final static String COUNTRY_CODE = "country";
    public final static String PROVINCE_CODE = "province";
    public final static String CITY_CODE = "city";
    public final static String DISTRICT_CODE = "district";
    public final static String STREET_CODE = "street";
}
