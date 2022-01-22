package in.hocg.rabbit.mall.biz.pojo.vo;

import in.hocg.boot.web.datastruct.KeyValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class CalcOrderVo {
    @ApiModelProperty("商品项")
    private List<OrderItem> items = Collections.emptyList();
    @ApiModelProperty("运费金额")
    private BigDecimal expressAmt;
    @ApiModelProperty("订单销售总额")
    private BigDecimal totalSaleAmt;
    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmt;
    @ApiModelProperty("实际订单总额 = 销售总额 - 优惠金额")
    private BigDecimal totalRealAmt;
    @ApiModelProperty("实际付款总额 = 订单总额 + 运费金额")
    private BigDecimal totalPayAmt;
    @ApiModelProperty("默认收货地址")
    private UserAddressVo defaultAddress;

    @ApiModelProperty("使用的优惠明细")
    private List<DiscountVo> usedDiscounts = Collections.emptyList();

    @ApiModelProperty("用户优惠券列表")
    private List<UserCouponVo> userCoupons = Collections.emptyList();

    @Data
    @ApiModel
    @Accessors(chain = true)
    public static class OrderItem {
        @ApiModelProperty("SKU ID")
        private Long skuId;
        @ApiModelProperty("PRODUCT ID")
        private Long productId;
        @ApiModelProperty("商品标题")
        private String title;
        @ApiModelProperty("商品图片")
        private String imageUrl;
        @ApiModelProperty("商品SKU条码")
        private String skuCode;
        @ApiModelProperty("规格")
        private List<KeyValue> spec = Collections.emptyList();

        @ApiModelProperty("购买数量")
        private Integer quantity;
        @ApiModelProperty("销售价")
        private BigDecimal salePrice;
        @ApiModelProperty("[计算型]原总价=销售价格x购买数量")
        private BigDecimal totalAmt;

        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmt;
        @ApiModelProperty("[计算型]优惠后金额=原总价-优惠金额")
        private BigDecimal realAmt;
    }

    @Data
    @ApiModel
    @Accessors(chain = true)
    public static class DiscountVo {
        @ApiModelProperty("优惠对象")
        private Long refId;
        @ApiModelProperty("优惠对象类型")
        private String refType;
        @ApiModelProperty("优惠信息类型")
        private String type;
        @ApiModelProperty("优惠标题")
        private String title;
        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmt;

    }

    @Data
    @ApiModel
    @Accessors(chain = true)
    public static class UserAddressVo {
        @ApiModelProperty("姓名")
        private String name;
        @ApiModelProperty("电话")
        private String tel;
        @ApiModelProperty("邮编")
        private String postcode;
        @ApiModelProperty("区域编码")
        private String adcode;
        @ApiModelProperty("省份")
        private String province;
        @ApiModelProperty("城市")
        private String city;
        @ApiModelProperty("区")
        private String region;
        @ApiModelProperty("详细地址")
        private String address;
    }

    @Data
    @ApiModel
    @Accessors(chain = true)
    public static class UserCouponVo {
        @ApiModelProperty("用户优惠券ID")
        private Long id;
        @ApiModelProperty("优惠券编号")
        private String encoding;
        @ApiModelProperty("优惠券标题")
        private String title;
        @ApiModelProperty("优惠券使用说明(优惠券可用时展示)")
        private String instructions;
        @ApiModelProperty("优惠券生效时间")
        private LocalDateTime startAt;
        @ApiModelProperty("优惠券失效时间")
        private LocalDateTime endAt;
        @ApiModelProperty("满减条件")
        private String condition;
        @ApiModelProperty("不可用原因，优惠券不可用时展示")
        private String reason;
        @ApiModelProperty("优惠金额文案(85或10)")
        private BigDecimal valueDesc;
        @ApiModelProperty("单位文案")
        private String unitDesc;
        @ApiModelProperty("是否可用")
        private Boolean usable = Boolean.FALSE;
        @ApiModelProperty("是否选中")
        private Boolean used = Boolean.FALSE;
        @ApiModelProperty("实际优惠金额(当选中时有效)")
        private BigDecimal usedAmount;
    }

}
