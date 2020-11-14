package com.github.lotus.chaos.module.wl.pojo.ro.logisticsline;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.BasicRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel("物流线路")
public class LogisticsLineBatchCreateRo extends BasicRo {
    @Size(min = 1, message = "请填写物流线路信息")
    @ApiModelProperty("物流线路")
    private List<LogisticsLineCreateRo> logisticsLines = Collections.emptyList();

    @ApiModelProperty(hidden = true)
    private Long creator;

}
