package com.github.lotus.chaos.module.wl.pojo.ro.logisticsline;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.BasicRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel("物流线路")
public class LogisticsLineCreateRo extends BasicRo {
    @NotNull(message = "请填写物流仓库")
    @ApiModelProperty("物流仓库")
    private Long warehouseId;
    @NotNull(message = "请填写省份")
    @ApiModelProperty("[终点]省区域编码")
    private String provinceAdcode;
    @NotNull(message = "请填写市区")
    @ApiModelProperty("[终点]市区区域编码")
    private String cityAdcode;
    @NotNull(message = "请填写县区")
    @ApiModelProperty("[终点]县区域编码")
    private String districtAdcode;
    @ApiModelProperty("单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("单位(元/方)")
    private String unit;
    @ApiModelProperty("时效(天)")
    private Integer aging;
    @ApiModelProperty("物流方式")
    private String shippingMethods;
    @Size(max = 255, message = "备注过长")
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(hidden = true)
    private Long creator;

}