package in.hocg.rabbit.wl.biz.pojo.vo;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.common.constant.DistrictLevelConstant;
import in.hocg.rabbit.wl.biz.enumns.ShippingMethods;
import in.hocg.rabbit.wl.biz.enumns.Unit;
import in.hocg.boot.named.annotation.InjectNamed;

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
    @ChaosNamed(idFor = "warehouseProvince", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.PROVINCE_CODE})
    private String warehouseProvinceName;
    @ApiModelProperty("仓库市")
    private String warehouseCity;
    @ChaosNamed(idFor = "warehouseCity", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.CITY_CODE})
    private String warehouseCityName;
    @ApiModelProperty("仓库县")
    private String warehouseDistrict;
    @ChaosNamed(idFor = "warehouseDistrict", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.DISTRICT_CODE})
    private String warehouseDistrictName;

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
    private String logisticsLineRemark;

    @ApiModelProperty("线路省")
    private String logisticsLineProvince;
    @ChaosNamed(idFor = "logisticsLineProvince", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.PROVINCE_CODE})
    private String logisticsLineProvinceName;
    @ApiModelProperty("线路市")
    private String logisticsLineCity;
    @ChaosNamed(idFor = "logisticsLineCity", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.CITY_CODE})
    private String logisticsLineCityName;
    @ApiModelProperty("线路县")
    private String logisticsLineDistrict;
    @ChaosNamed(idFor = "logisticsLineDistrict", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.DISTRICT_CODE})
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
