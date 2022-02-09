package in.hocg.rabbit.mall.biz.convert;

import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mall.biz.entity.Coupon;
import in.hocg.rabbit.mall.biz.entity.StintRule;
import in.hocg.rabbit.mall.biz.mapstruct.CouponMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.CouponOrdinaryVo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleComplexVo;
import in.hocg.rabbit.mall.biz.service.StintRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CouponConvert {
    private final CouponMapping mapping;
    private final StintRuleService stintRuleService;

    public CouponComplexVo convertComplex(Coupon entity) {
        Long entityId = entity.getId();
        List<StintRule> stintRules = stintRuleService.listByCouponId(entityId);

        return mapping.asCouponComplexVo(entity)
            .setStintRules(LangUtils.toList(stintRules, item -> stintRuleService.as(item, StintRuleComplexVo.class)));
    }

    public CouponOrdinaryVo asCouponOrdinaryVo(Coupon entity) {
        return mapping.asCouponOrdinaryVo(entity);
    }
}
