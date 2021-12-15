package in.hocg.rabbit.wl.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.common.constant.DistrictLevelConstant;
import in.hocg.rabbit.wl.biz.enumns.ShippingMethods;
import in.hocg.rabbit.wl.biz.enumns.Unit;
import in.hocg.boot.named.annotation.InjectNamed;

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
    @ChaosNamed(idFor = "unit", type = ChaosNamedType.DataDictName, args = {Unit.KEY})
    private String unitName;
    @ApiModelProperty("时效(天)")
    private Integer aging;
    @ApiModelProperty("物流方式")
    private String shippingMethods;
    @ChaosNamed(idFor = "shippingMethods", type = ChaosNamedType.DataDictName,
        args = {ShippingMethods.KEY})
    private String shippingMethodsName;
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("省区域编码")
    private String provinceAdcode;
    @ChaosNamed(idFor = "provinceAdcode", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.PROVINCE_CODE})
    private String provinceName;
    @ApiModelProperty("市区区域编码")
    private String cityAdcode;
    @ChaosNamed(idFor = "cityAdcode", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.CITY_CODE})
    private String cityName;
    @ApiModelProperty("县区域编码")
    private String districtAdcode;
    @ChaosNamed(idFor = "districtAdcode", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.DISTRICT_CODE})
    private String districtName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @ChaosNamed(idFor = "lastUpdater", type = ChaosNamedType.Userid2Nickname)
    private String lastUpdaterName;

    @ApiModelProperty("仓库")
    private List<WarehouseComplexVo> warehouses = Collections.emptyList();
}
