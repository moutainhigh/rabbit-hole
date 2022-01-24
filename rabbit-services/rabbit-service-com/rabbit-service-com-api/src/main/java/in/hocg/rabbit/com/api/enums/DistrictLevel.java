package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/8/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum DistrictLevel implements DataDictEnum {
    Country("country", "国家"),
    Province("province", "省份"),
    City("city", "城市"),
    District("district", "区域"),
    Street("street", "街道");
    private final Serializable code;
    private final String name;

    public final static String KEY = "district_level";
}
