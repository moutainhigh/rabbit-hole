package com.github.lotus.com.biz.pojo.vo;

import in.hocg.boot.web.autoconfiguration.jackson.bigdecimal.BigDecimalFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
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
    @ApiModelProperty("今日签到状态")
    private Boolean hasSigned = Boolean.FALSE;
    @ApiModelProperty("观看视频是否到上限")
    private Boolean hasWatchAdUpperLimit = Boolean.FALSE;
}
