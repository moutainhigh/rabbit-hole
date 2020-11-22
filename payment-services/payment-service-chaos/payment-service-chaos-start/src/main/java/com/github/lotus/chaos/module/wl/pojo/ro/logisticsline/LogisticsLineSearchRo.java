package com.github.lotus.chaos.module.wl.pojo.ro.logisticsline;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

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
    @ApiModelProperty("货物信息")
    private List<GoodsRo> goods = Collections.emptyList();

    @Data
    @ApiModel
    public static class Point {
        @ApiModelProperty("省区域编码")
        private String provinceAdcode;
        @ApiModelProperty("市区区域编码")
        private String cityAdcode;
        @ApiModelProperty("县区域编码")
        private String districtAdcode;
    }

    @Data
    @ApiModel
    public static class GoodsRo {
        @NotNull(message = "请补充长")
        @ApiModelProperty("长")
        private Long l;
        @NotNull(message = "请补充宽")
        @ApiModelProperty("宽")
        private Long w;
        @NotNull(message = "请补充高")
        @ApiModelProperty("高")
        private Long h;
        @ApiModelProperty("重量")
        private Long weigh;
        @NotNull(message = "请补充数量")
        @ApiModelProperty("数量")
        private Long quantity;

        public BigDecimal getTotalPrice(BigDecimal unitPrice) {
            try {
                return BigDecimal.valueOf(l/100 * w/100 * h/100 * quantity).multiply(unitPrice);
            } catch (Exception e) {
                return null;
            }
        }
    }
}
