package com.github.lotus.chaos.module.wl.pojo.vo;

import com.github.lotus.chaos.module.com.enums.DistrictLevel;
import com.github.lotus.chaos.module.wl.enumns.ShippingMethods;
import com.github.lotus.chaos.module.wl.enumns.Unit;
import com.github.lotus.chaos.modules.support.api.ChaosNamedAPI;
import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
import in.hocg.boot.named.autoconfiguration.annotation.Named;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/11/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
@InjectNamed
public class LogisticsLineSearchVo {

    @ApiModelProperty("公司名称")
    private String companyName;
    @ApiModelProperty("公司备注")
    private String companyRemark;
    @ApiModelProperty("公司号码")
    private String companyTel;

    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("仓库备注")
    private String warehouseRemark;
    @ApiModelProperty("仓库省")
    private String warehouseProvince;
    @Named(idFor = "warehouseProvince", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.PROVINCE_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String warehouseProvinceName;
    @ApiModelProperty("仓库市")
    private String warehouseCity;
    @Named(idFor = "warehouseCity", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.CITY_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String warehouseCityName;
    @ApiModelProperty("仓库县")
    private String warehouseDistrict;
    @Named(idFor = "warehouseDistrict", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.DISTRICT_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String warehouseDistrictName;

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
    private String logisticsLineRemark;

    @ApiModelProperty("线路省")
    private String logisticsLineProvince;
    @Named(idFor = "logisticsLineProvince", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.PROVINCE_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String logisticsLineProvinceName;
    @ApiModelProperty("线路市")
    private String logisticsLineCity;
    @Named(idFor = "logisticsLineCity", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.CITY_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String logisticsLineCityName;
    @ApiModelProperty("线路县")
    private String logisticsLineDistrict;
    @Named(idFor = "logisticsLineDistrict", type = ChaosNamedAPI.COM_DistrictName,
        args = {DistrictLevel.DISTRICT_CODE},
        serviceClass = ChaosNamedAPI.class)
    private String logisticsLineDistrictName;

    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;

    @ApiModelProperty("货物信息")
    private List<GoodsVo> goods = Collections.emptyList();

    @ApiModelProperty("预估总费用")
    private BigDecimal totalPrice;

    @Data
    @ApiModel
    @Accessors(chain = true)
    public static class GoodsVo {
        @ApiModelProperty("长")
        private Long l;
        @ApiModelProperty("宽")
        private Long w;
        @ApiModelProperty("高")
        private Long h;
        @ApiModelProperty("重量")
        private Long weigh;
        @ApiModelProperty("数量")
        private Long quantity;
        @ApiModelProperty("价格")
        private BigDecimal totalPrice;
    }
}
