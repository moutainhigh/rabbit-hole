package in.hocg.rabbit.mall.biz.support.mode.def.discount;

import in.hocg.rabbit.mall.biz.support.mode.def.rule.ExpiredAtRule;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/1/18
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class Discounts {

    /**
     * 管理员调价优惠
     *
     * @param id            优惠id
     * @param adjustmentAmt 调价金额
     * @return 优惠
     */
    public AdjustmentDiscount createAdjustmentDiscount(Serializable id, BigDecimal adjustmentAmt) {
        return new AdjustmentDiscount(id, adjustmentAmt);
    }
}
