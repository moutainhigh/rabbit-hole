package com.github.lotus.chaos.module.wl.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.lotus.chaos.module.com.enums.DistrictLevel;
import com.github.lotus.chaos.modules.support.ChaosNamedAPI;
import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
import in.hocg.boot.named.autoconfiguration.annotation.Named;
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
@ApiModel("物流仓库")
public class WarehouseComplexVo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    private String title;
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("省区域编码")
    private String provinceAdcode;
    @Named(idFor = "provinceAdcode", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.PROVINCE_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String provinceName;
    @ApiModelProperty("市区区域编码")
    private String cityAdcode;
    @Named(idFor = "cityAdcode", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.CITY_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String cityName;
    @ApiModelProperty("县区域编码")
    private String districtAdcode;
    @Named(idFor = "districtAdcode", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.DISTRICT_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String districtName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @Named(idFor = "creator", type = ChaosNamedAPI.USERID2NICKNAME,
        serviceClass = ChaosNamedAPI.class)
    private String creatorName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @Named(idFor = "lastUpdater", type = ChaosNamedAPI.USERID2NICKNAME,
        serviceClass = ChaosNamedAPI.class)
    private String lastUpdaterName;

    @ApiModelProperty("物流公司")
    private CompanyComplexVo company;
}
