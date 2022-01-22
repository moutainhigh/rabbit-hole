package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.web.datastruct.KeyValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2020/3/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@Accessors(chain = true)
public class OrderItemComplexVo {
    @ApiModelProperty("OrderItem ID")
    private Long id;
    @ApiModelProperty("Product ID")
    private Long productId;
    @ApiModelProperty("商品名称")
    private String title;
    @ApiModelProperty("商品购买数量")
    private Integer quantity;
    @ApiModelProperty("商品主图")
    private String imageUrl;
    @ApiModelProperty("销售价")
    private BigDecimal salePrice;
    @ApiModelProperty("[计算型]原总价=销售价格x购买数量")
    private BigDecimal totalAmt;
    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmt;
    @ApiModelProperty("[计算型]优惠后金额=原总价-优惠金额")
    private BigDecimal realAmt;

    @ApiModelProperty("商品购买时SKU ID")
    private Long skuId;
    @ApiModelProperty("商品购买时SKU 编码")
    private String skuCode;
    @ApiModelProperty("规格")
    private List<KeyValue> spec;
}
