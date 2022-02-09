package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
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
@UseDataDictKey(value = ComDataDictKeys.DISTRICT_LEVEL, description = "行政区划级别")
public enum DistrictLevel implements DataDictEnum {
    Country("country", "国家"),
    Province("province", "省份"),
    City("city", "城市"),
    District("district", "区域"),
    Street("street", "街道");
    private final Serializable code;
    private final String name;

}
