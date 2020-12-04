package com.github.lotus.chaos.module.wl.pojo.ro.warehouse;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.BasicRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel("物流仓库")
public class WarehouseCreateRo extends BasicRo {
    @NotNull(message = "请填写仓库名称(3~10)")
    @Size(min = 3, max = 10, message = "请填写仓库名称(3~10)")
    @ApiModelProperty("仓库名称")
    private String title;
    @NotNull
    @ApiModelProperty("物流公司")
    private Long companyId;
    @ApiModelProperty("省")
    private String provinceAdcode;
    @ApiModelProperty("市")
    private String cityAdcode;
    @ApiModelProperty("县")
    private String districtAdcode;
    @Size(max = 255, message = "备注过长")
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(hidden = true)
    private Long creator;
}
