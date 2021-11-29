package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CalcOrderRo {
    @NotNull
    @Size(min = 1)
    @ApiModelProperty("订单项")
    private List<Item> items = Collections.emptyList();
    @ApiModelProperty("优惠券ID")
    private Long selectedCouponId;

    @Data
    public static class Item {
        @NotNull(message = "请选择商品")
        @ApiModelProperty("购买商品的SKU")
        private Long skuId;
        @NotNull(message = "请选择数量")
        @Min(value = 1)
        @ApiModelProperty("购买数量")
        private Integer quantity;
    }

    @ApiModelProperty(hidden = true)
    private Long userId;
}
