package com.github.lotus.com.biz.pojo.vo.district;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ApiModel
@Data
public class DistrictCompleteVo {
    @ApiModelProperty("国家")
    private String country;
    private String countryAdcode;
    @ApiModelProperty("省份")
    private String province;
    private String provinceAdcode;
    @ApiModelProperty("城市")
    private String city;
    private String cityAdcode;
    @ApiModelProperty("县/区")
    private String district;
    private String districtAdcode;
    @ApiModelProperty("街道")
    private String street;
    private String streetAdcode;
}
