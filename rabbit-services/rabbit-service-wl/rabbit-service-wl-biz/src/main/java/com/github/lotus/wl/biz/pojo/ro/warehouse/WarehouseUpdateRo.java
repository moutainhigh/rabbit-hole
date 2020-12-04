package com.github.lotus.wl.biz.pojo.ro.warehouse;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.BasicRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel("物流仓库")
public class WarehouseUpdateRo extends BasicRo {
    @Size(min = 3, max = 10, message = "仓库名称名称错误")
    @ApiModelProperty("仓库名称")
    private String title;
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
    private Long updater;
}
