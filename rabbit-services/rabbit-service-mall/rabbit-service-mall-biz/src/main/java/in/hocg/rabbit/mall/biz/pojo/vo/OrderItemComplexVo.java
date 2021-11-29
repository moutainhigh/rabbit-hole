package in.hocg.rabbit.mall.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.web.datastruct.KeyValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class OrderItemComplexVo {
    @ApiModelProperty("OrderItem ID")
    private Long id;
    @ApiModelProperty("Product ID")
    private Long productId;
    @ApiModelProperty("商品名称")
    private String title;
    @ApiModelProperty("销售单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("商品购买数量")
    private Integer quantity;
    @ApiModelProperty("商品主图")
    private String imageUrl;
    @ApiModelProperty("商品购买时SKU ID")
    private Long skuId;
    @ApiModelProperty("商品购买时SKU 编码")
    private String skuCode;
    private List<KeyValue> spec;
    @ApiModelProperty("商品购买规格")
    private String skuSpecData;

    @ApiModelProperty("优惠分解金额(不含后台调整)")
    private BigDecimal discountAmt;
    @ApiModelProperty("后台调整优惠")
    private BigDecimal adjustmentDiscountAmt;
    @ApiModelProperty("[计算型]原总价=销售价格x购买数量")
    private BigDecimal totalAmt;
    @ApiModelProperty("[计算型]该商品经过优惠后的分解金额(实际支付金额)=原总价-后台调整优惠-优惠分解金额")
    private BigDecimal userPayAmt;

    @ApiModelProperty("退费申请")
    private List<OrderRefundApplyComplexVo> refundApplyItems;
}
