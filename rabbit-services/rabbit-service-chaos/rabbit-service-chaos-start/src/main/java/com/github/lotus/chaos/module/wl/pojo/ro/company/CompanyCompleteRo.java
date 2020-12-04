package com.github.lotus.chaos.module.wl.pojo.ro.company;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.CompleteRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel("搜索 - 物流公司")
public class CompanyCompleteRo extends CompleteRo {
    @ApiModelProperty("关键字")
    private String keyword;
}
