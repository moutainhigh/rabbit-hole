package com.github.lotus.com.biz.pojo.vo;

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
    @ApiModelProperty("获取的总积分")
    private BigDecimal total;
    @ApiModelProperty("可用积分")
    private BigDecimal available;
    @ApiModelProperty("已用积分")
    private BigDecimal used;
}
