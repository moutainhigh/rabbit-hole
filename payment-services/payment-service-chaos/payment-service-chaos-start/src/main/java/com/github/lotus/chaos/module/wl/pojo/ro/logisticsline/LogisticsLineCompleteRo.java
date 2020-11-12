package com.github.lotus.chaos.module.wl.pojo.ro.logisticsline;

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
@ApiModel
public class LogisticsLineCompleteRo extends CompleteRo {
    @ApiModelProperty("关键字")
    private String keyword;
}
