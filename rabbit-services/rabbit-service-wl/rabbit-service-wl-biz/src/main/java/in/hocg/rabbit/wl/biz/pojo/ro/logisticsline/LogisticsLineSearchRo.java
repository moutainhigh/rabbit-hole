package in.hocg.rabbit.wl.biz.pojo.ro.logisticsline;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    @Valid
    @ApiModelProperty("起点 [省,市,县]")
    private Point starPoint;
    @Valid
    @ApiModelProperty("终点 [省,市,县]")
    private Point endPoint;
    @Valid
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
        @Max(value = 10000L, message = "长不能超过10000")
        @Min(value = 0L, message = "长不符合")
        @NotNull(message = "请补充长")
        @ApiModelProperty("长")
        private Long l;
        @Max(value = 10000L, message = "宽不能超过10000")
        @Min(value = 0L, message = "宽不符合")
        @NotNull(message = "请补充宽")
        @ApiModelProperty("宽")
        private Long w;
        @Max(value = 10000L, message = "高不能超过10000")
        @Min(value = 0L, message = "高不符合")
        @NotNull(message = "请补充高")
        @ApiModelProperty("高")
        private Long h;
        @ApiModelProperty("重量")
        private Long weigh;
        @Max(value = 100L, message = "数量不能超过100")
        @Min(value = 0L, message = "数量不符合")
        @NotNull(message = "请补充数量")
        @ApiModelProperty("数量")
        private Long quantity;

        public BigDecimal getTotalPrice(BigDecimal unitPrice) {
            try {
                return BigDecimal.valueOf(l)
                    .multiply(BigDecimal.valueOf(w))
                    .multiply(BigDecimal.valueOf(h))
                    .multiply(BigDecimal.valueOf(quantity))
                    .multiply(unitPrice)
                    .divide(BigDecimal.valueOf(100_00_00L), 2, RoundingMode.DOWN);
            } catch (Exception e) {
                return null;
            }
        }
    }
}
