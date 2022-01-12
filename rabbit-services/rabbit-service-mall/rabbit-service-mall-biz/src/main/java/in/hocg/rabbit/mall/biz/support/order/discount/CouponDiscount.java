package in.hocg.rabbit.mall.biz.support.order.discount;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.rabbit.mall.api.enums.coupon.CouponType;
import in.hocg.rabbit.mall.biz.support.mode.*;
import in.hocg.rabbit.mall.biz.support.mode.utils.MathUtils;
import in.hocg.rabbit.mall.biz.support.order.MallOrderContext;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * Created by hocgin on 2022/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Accessors(chain = true)
public class CouponDiscount extends Discount<MallOrderContext> {
    @Setter
    @Getter
    @ApiModelProperty("折扣方式：[fixed_amt=>满减；scale_amt=>折扣]")
    private String type;
    @Setter
    @Getter
    @ApiModelProperty("满减金额/折扣率")
    private BigDecimal credit;
    @Setter
    @Getter
    @ApiModelProperty("优惠上限")
    private BigDecimal ceilingAmt;

    public CouponDiscount(Serializable id, List<Rule<MallOrderContext>> rules) {
        super(id, rules);
    }

    @Override
    public DiscountResult handle(MallOrderContext context, DiscountResult result) {
        if (ICode.ofThrow(type, CouponType.class).equals(CouponType.FixedAmt)) {
            setDiscountAmt(MathUtils.min(credit, ceilingAmt));
        } else if (ICode.ofThrow(type, CouponType.class).equals(CouponType.ScaleAmt)) {
            BigDecimal scaleDiscountAmt = result.getConform().stream().map(OrderSupport.Item::getId)
                .map(context::getRealAmtByOrderItem).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            setDiscountAmt(MathUtils.min(scaleDiscountAmt, ceilingAmt));
        } else {
            throw new IllegalArgumentException();
        }
        return super.handle(context, result);
    }
}
