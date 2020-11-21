package com.github.lotus.chaos.module.wl.pojo.ro.logisticsline;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class LogisticsLineSearchRo extends PageRo {
    @ApiModelProperty("起点 [省,市,县]")
    private Point starPoint;
    @ApiModelProperty("终点 [省,市,县]")
    private Point endPoint;

    @ApiModel
    @Data
    public static class Point {
        @ApiModelProperty("省区域编码")
        private String provinceAdcode;
        @ApiModelProperty("市区区域编码")
        private String cityAdcode;
        @ApiModelProperty("县区域编码")
        private String districtAdcode;
    }
}
