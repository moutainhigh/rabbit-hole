package com.github.lotus.chaos.module.wl.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.lotus.chaos.module.wl.enumns.ShippingMethods;
import com.github.lotus.chaos.module.wl.enumns.Unit;
import com.github.lotus.chaos.module.com.enums.DistrictLevel;
import com.github.lotus.chaos.modules.support.api.ChaosNamedAPI;
import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
import in.hocg.boot.named.autoconfiguration.annotation.Named;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@ApiModel("物流线路")
public class LogisticsLineComplexVo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("单位(元/方)")
    private String unit;
    @Named(idFor = "unit", type = ChaosNamedAPI.DATA_DICT,
        args = {Unit.KEY}, serviceClass = ChaosNamedAPI.class)
    private String unitName;
    @ApiModelProperty("时效(天)")
    private Integer aging;
    @ApiModelProperty("物流方式")
    private String shippingMethods;
    @Named(idFor = "shippingMethods", type = ChaosNamedAPI.DATA_DICT,
        args = {ShippingMethods.KEY}, serviceClass = ChaosNamedAPI.class)
    private String shippingMethodsName;
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

    @ApiModelProperty("仓库")
    private List<WarehouseComplexVo> warehouses = Collections.emptyList();
}
