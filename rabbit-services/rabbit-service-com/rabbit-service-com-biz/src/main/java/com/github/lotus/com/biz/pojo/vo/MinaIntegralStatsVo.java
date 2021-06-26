package com.github.lotus.com.biz.pojo.vo;

import in.hocg.boot.web.autoconfiguration.jackson.bigdecimal.BigDecimalFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class MinaIntegralStatsVo {
    @BigDecimalFormat
    @ApiModelProperty("获取的总积分")
    private BigDecimal total;
    @BigDecimalFormat
    @ApiModelProperty("可用积分")
    private BigDecimal available;
    @BigDecimalFormat
    @ApiModelProperty("已用积分")
    private BigDecimal used;
}
