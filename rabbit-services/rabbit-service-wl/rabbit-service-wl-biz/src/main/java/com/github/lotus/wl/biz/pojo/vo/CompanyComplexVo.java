package com.github.lotus.wl.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedApi;
import com.github.lotus.chaos.api.NamedType;
import com.github.lotus.common.constant.DistrictLevelConstant;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@ApiModel(description = "公司信息")
public class CompanyComplexVo {
    private Long id;
    @ApiModelProperty("名称")
    private String title;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("tel")
    private String tel;

    @ApiModelProperty("省区域编码")
    private String provinceAdcode;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "provinceAdcode", type = NamedType.DistrictName,
        args = {DistrictLevelConstant.PROVINCE_CODE})
    private String provinceName;
    @ApiModelProperty("市区区域编码")
    private String cityAdcode;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "cityAdcode", type = NamedType.DistrictName,
        args = {DistrictLevelConstant.CITY_CODE})
    private String cityName;
    @ApiModelProperty("县区域编码")
    private String districtAdcode;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "districtAdcode", type = NamedType.DistrictName,
        args = {DistrictLevelConstant.DISTRICT_CODE})
    private String districtName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;

}
