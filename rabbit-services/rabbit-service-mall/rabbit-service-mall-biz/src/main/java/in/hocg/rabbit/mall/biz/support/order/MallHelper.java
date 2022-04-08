package in.hocg.rabbit.mall.biz.support.order;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.mall.api.enums.coupon.CouponType;
import in.hocg.rabbit.mall.api.enums.order.OrderDiscountType;
import in.hocg.rabbit.mall.api.enums.rule.StintRuleType;
import in.hocg.rabbit.mall.biz.entity.*;
import in.hocg.rabbit.mall.biz.pojo.ro.CalcOrderRo;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.service.CouponService;
import in.hocg.rabbit.mall.biz.service.StintRuleService;
import in.hocg.rabbit.mall.biz.support.mode.*;
import in.hocg.rabbit.mall.biz.support.mode.def.rule.Rules;
import in.hocg.rabbit.common.utils.MathUtils;
import in.hocg.rabbit.mall.biz.support.order.discount.CouponDiscount;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2022/1/18
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class MallHelper {

    public Discount<MallOrderContext> getDiscountByUserCoupon(UserCoupon userCoupon) {
        return new CouponDiscount(userCoupon.getId(), getRuleByCoupon(userCoupon));
    }

    public List<Rule<MallOrderContext>> getRuleByCoupon(UserCoupon userCoupon) {
        Long couponId = userCoupon.getCouponId();
        LocalDateTime startAt = userCoupon.getStartAt();

        List<Rule<MallOrderContext>> result = Lists.newArrayList();
        List<StintRule> rules = stintRuleService().listByCouponId(couponId);
        for (StintRule rule : rules) {
            Long id = rule.getId();
            String ruleStr = rule.getRule();
            StintRuleType ruleType = ICode.ofThrow(rule.getType(), StintRuleType.class);
            if (ruleType == StintRuleType.AvailablePlatform) {
                result.add(MallRuleHelper.createAvailablePlatformRule(id, JSON.parseArray(ruleStr, String.class)));
            } else {
                throw new UnsupportedOperationException();
            }
        }
        result.add(Rules.createEnableAtRule(userCoupon.getId(), startAt));
        result.add(Rules.createExpiredAtRule(userCoupon.getId(), startAt));
        return result;
    }

    public OrderSupport.Item asItem(OrderItem item) {
        return new OrderSupport.Item(item.getId(), item.getRealAmt());
    }

    public CalcOrderVo.OrderItem asItem(CalcOrderRo.Item item, Product product, Sku sku) {
        String imageUrl = LangUtils.getOrDefault(sku.getImageUrl(), null);
        List<KeyValue> spec = JSON.parseArray(sku.getSpecData(), KeyValue.class);
        BigDecimal totalAmt = MathUtils.multiply(sku.getUnitPrice(), BigDecimal.valueOf(item.getQuantity()));
        return new CalcOrderVo.OrderItem()
            .setSkuCode(sku.getEncoding())
            .setProductId(product.getId())
            .setSkuId(sku.getId())
            .setSpec(spec)
            .setQuantity(item.getQuantity())
            .setSalePrice(sku.getUnitPrice())
            .setTotalAmt(totalAmt)
            .setImageUrl(imageUrl)
            .setTitle(product.getTitle());
    }

    public static CalcOrderVo.UserCouponVo asUserCoupon(Map<Long, UserCoupon> userCouponMap, DiscountResult result) {
        Discount<? extends OrderContext> discount = result.getDiscount();
        Serializable discountId = discount.getId();
        UserCoupon userCoupon = userCouponMap.get(Convert.toLong(discountId));
        Coupon coupon = couponService().getById(userCoupon.getCouponId());
        String unitDesc = CouponType.FixedAmt.eq(coupon.getType()) ? "元" : "折";
        return new CalcOrderVo.UserCouponVo()
            .setValueDesc(coupon.getCeilingAmt())
            .setUnitDesc(unitDesc)
            .setReason(result.getFirstErrorMessage())
            .setTitle(OrderDiscountType.UserCoupon.getName())
            .setId(userCoupon.getId())
            .setEncoding(userCoupon.getEncoding())
            .setEndAt(userCoupon.getEndAt())
            .setStartAt(userCoupon.getStartAt())
            .setUsable(result.getUsable())
            .setUsed(discount.isUsed());
    }

    public static boolean isUserCoupon(DiscountResult result) {
        return result.getDiscount().getClass().isAssignableFrom(CouponDiscount.class);
    }

    public static CalcOrderVo.DiscountVo asDiscountVo(DiscountResult ro) {
        CalcOrderVo.DiscountVo result = new CalcOrderVo.DiscountVo();
        Discount<? extends OrderContext> discount = ro.getDiscount();
        if (isUserCoupon(ro)) {
            result.setDiscountAmt(discount.getDiscountAmt());
            result.setRefId((Long) discount.getId());
            result.setRefType(RefType.UserCoupon.getCodeStr());
            result.setType(OrderDiscountType.UserCoupon.getCodeStr());
            result.setTitle(OrderDiscountType.UserCoupon.getName());
        } else {
            throw new UnsupportedOperationException();
        }
        return result;
    }

    public static MallOrderContext createContext() {
        return new MallOrderContext();
    }

    private static StintRuleService stintRuleService() {
        return SpringServletContext.getBean(StintRuleService.class);
    }

    private static CouponService couponService() {
        return SpringServletContext.getBean(CouponService.class);
    }
}
