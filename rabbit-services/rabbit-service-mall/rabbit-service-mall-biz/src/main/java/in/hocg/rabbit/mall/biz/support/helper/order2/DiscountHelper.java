package in.hocg.rabbit.mall.biz.support.helper.order2;

import in.hocg.rabbit.mall.api.enums.CouponType;
import in.hocg.rabbit.mall.api.enums.CouponUsePlatform;
import in.hocg.rabbit.mall.api.enums.CouponUseStint;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.UserCouponComplexVo;
import in.hocg.rabbit.mall.biz.support.helper.order2.discount.Discount;
import in.hocg.rabbit.mall.biz.support.helper.order2.discount.coupon.FixedAmountCoupon;
import in.hocg.rabbit.mall.biz.support.helper.order2.discount.coupon.FixedScaleCoupon;
import in.hocg.rabbit.mall.biz.support.helper.order2.rule.Rule;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.rabbit.mall.biz.support.helper.order2.rule.rule.*;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2020/7/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class DiscountHelper {

    public Discount createCoupon(UserCouponComplexVo vo) {
        final String couponType = vo.getCouponType();
        final BigDecimal credit = vo.getCredit();
        final List<Rule> rules = getCouponRules(vo);
        final Long id = vo.getId();
        if (CouponType.FixedAmt.eq(couponType)) {
            return new FixedAmountCoupon(credit, rules).setId(id);
        } else if (CouponType.ScaleAmt.eq(couponType)) {
            return new FixedScaleCoupon(credit, vo.getCeiling(), rules).setId(id);
        }
        throw ServiceException.wrap("不支持优惠券类型");
    }

    private List<Rule> getCouponRules(UserCouponComplexVo vo) {
        final List<Rule> rules = Lists.newArrayList();
        rules.add(new UsableStatusRule(vo.getStatus()));
        rules.add(new UsableDatetimeRangeRule(vo.getStartAt(), vo.getEndAt()));
        rules.add(new OrderMinPointRule(vo.getMinPoint()));
        final String useStint = vo.getUseStint();
        String usePlatform = vo.getUsePlatform();

        // 商品
        if (CouponUseStint.Product.eq(useStint)) {
            final List<Long> canUseProductId = ((List<ProductComplexVo>) LangUtils.getOrDefault(vo.getCanUseProduct(), Lists.newArrayList())).parallelStream()
                .map(ProductComplexVo::getId).collect(Collectors.toList());
            rules.add(new UsableProductRule(canUseProductId));
        }
        // 品类
        else if (CouponUseStint.Category.eq(useStint)) {
            final List<Long> canUseProductCategoryId = ((List<ProductCategoryComplexVo>) LangUtils.getOrDefault(vo.getCanUseProductCategory(), Lists.newArrayList())).parallelStream()
                .map(ProductCategoryComplexVo::getId).collect(Collectors.toList());
            rules.add(new UsableProductRule(canUseProductCategoryId));
        }
        if (!CouponUsePlatform.None.eq(usePlatform)) {
            rules.add(new UsablePlatformRule(usePlatform));
        }
        return rules;
    }

}
