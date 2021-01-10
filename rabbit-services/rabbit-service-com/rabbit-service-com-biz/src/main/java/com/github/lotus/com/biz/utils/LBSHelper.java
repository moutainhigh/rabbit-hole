package com.github.lotus.com.biz.utils;

import com.github.lotus.common.constant.DistrictLevelConstant;
import com.github.lotus.common.datadict.DistrictLevel;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class LBSHelper {

    public String getCitycode(String citycode) {
        return "[]".equals(citycode) ? null : citycode;
    }

    public List<BigDecimal> getLngLat(String center) {
        try {
            final String[] lnglat = center.split(",");
            return Lists.newArrayList(new BigDecimal(lnglat[0]), new BigDecimal(lnglat[1]));
        } catch (Exception e) {
            return Lists.newArrayList(null, null);
        }
    }

    public BigDecimal getLat(String center) {
        return LBSHelper.getLngLat(center).get(1);
    }

    public BigDecimal getLng(String center) {
        return LBSHelper.getLngLat(center).get(0);
    }

    /**
     * 位置级别
     *
     * @param level
     * @return
     */
    public DistrictLevel ofLevel(String level) {
        switch (level) {
            case DistrictLevelConstant.COUNTRY_CODE:
                return DistrictLevel.Country;
            case DistrictLevelConstant.PROVINCE_CODE:
                return DistrictLevel.Province;
            case DistrictLevelConstant.CITY_CODE:
                return DistrictLevel.City;
            case DistrictLevelConstant.DISTRICT_CODE:
                return DistrictLevel.District;
            case DistrictLevelConstant.STREET_CODE:
                return DistrictLevel.Street;
            default:
                throw new UnsupportedOperationException("未知的级别: " + level);
        }
    }
}
