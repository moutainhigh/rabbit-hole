package com.github.lotus.wl.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.chaos.api.NamedType;
import com.github.lotus.common.constant.DistrictLevelConstant;
import com.github.lotus.wl.biz.enumns.ShippingMethods;
import com.github.lotus.wl.biz.enumns.Unit;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
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
@ApiModel(description = "物流线路")
public class LogisticsLineComplexVo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("单位(元/方)")
    private String unit;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "unit", type = NamedType.DataDict, args = {Unit.KEY})
    private String unitName;
    @ApiModelProperty("时效(天)")
    private Integer aging;
    @ApiModelProperty("物流方式")
    private String shippingMethods;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "shippingMethods", type = NamedType.DataDict,
        args = {ShippingMethods.KEY})
    private String shippingMethodsName;
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("省区域编码")
    private String provinceAdcode;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "provinceAdcode", type = NamedType.DistrictName,
        args = {DistrictLevelConstant.PROVINCE_CODE})
    private String provinceName;
    @ApiModelProperty("市区区域编码")
    private String cityAdcode;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "cityAdcode", type = NamedType.DistrictName,
        args = {DistrictLevelConstant.CITY_CODE})
    private String cityName;
    @ApiModelProperty("县区域编码")
    private String districtAdcode;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "districtAdcode", type = NamedType.DistrictName,
        args = {DistrictLevelConstant.DISTRICT_CODE})
    private String districtName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;

    @ApiModelProperty("仓库")
    private List<WarehouseComplexVo> warehouses = Collections.emptyList();
}
