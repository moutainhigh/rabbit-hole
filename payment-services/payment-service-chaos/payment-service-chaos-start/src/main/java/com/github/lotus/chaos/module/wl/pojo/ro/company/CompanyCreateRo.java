package com.github.lotus.chaos.module.wl.pojo.ro.company;

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
@ApiModel("创建物流公司")
public class CompanyCreateRo extends BasicRo {
    @NotNull(message = "物流公司名称错误")
    @Size(min = 3, max = 10, message = "物流公司名称错误")
    @ApiModelProperty("物流公司名称")
    private String title;
    @ApiModelProperty("物流公司电话")
    private String tel;
    @ApiModelProperty("公司备注")
    private String remark;
    @ApiModelProperty("省")
    private String provinceAdcode;
    @ApiModelProperty("市")
    private String cityAdcode;
    @ApiModelProperty("县")
    private String countyAdcode;

    @ApiModelProperty(hidden = true)
    private Long creator;
}
