package com.github.lotus.common.datadict;

import com.github.lotus.common.constant.DistrictLevelConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2020/8/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum DistrictLevel {
    Country(DistrictLevelConstant.COUNTRY_CODE, "国家"),
    Province(DistrictLevelConstant.PROVINCE_CODE, "省份"),
    City(DistrictLevelConstant.CITY_CODE, "城市"),
    District(DistrictLevelConstant.DISTRICT_CODE, "区域"),
    Street(DistrictLevelConstant.STREET_CODE, "街道");
    private final String code;
    private final String name;

    public final static String KEY = "DistrictLevel";
}
