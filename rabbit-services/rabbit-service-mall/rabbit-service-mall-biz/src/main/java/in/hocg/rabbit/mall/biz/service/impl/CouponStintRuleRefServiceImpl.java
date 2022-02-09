package in.hocg.rabbit.mall.biz.service.impl;

import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mall.biz.entity.CouponStintRuleRef;
import in.hocg.rabbit.mall.biz.mapper.CouponStintRuleRefMapper;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.mall.biz.service.CouponStintRuleRefService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * <p>
 * [促销模块] 优惠券-限制规则表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CouponStintRuleRefServiceImpl extends AbstractServiceImpl<CouponStintRuleRefMapper, CouponStintRuleRef> implements CouponStintRuleRefService {

    @Override
    public boolean hasUsedStintRule(List<Long> ruleIds) {
        return lambdaQuery().in(CouponStintRuleRef::getRuleId, ruleIds).count() > 0;
    }

    @Override
    public void saveBatchByCouponId(Long couponId, List<Long> ruleId) {
        List<CouponStintRuleRef> entities = ruleId.parallelStream()
            .map(id -> new CouponStintRuleRef().setRuleId(id).setCouponId(couponId))
            .collect(Collectors.toList());
        List<CouponStintRuleRef> allData = this.listByCouponId(couponId);

        final BiFunction<CouponStintRuleRef, CouponStintRuleRef, Boolean> isSame =
            (t1, t2) -> LangUtils.equals(t1.getCouponId(), t2.getCouponId());
        final List<CouponStintRuleRef> mixedList = LangUtils.getMixed(allData, entities, isSame);
        List<CouponStintRuleRef> deleteList = LangUtils.removeIfExits(allData, mixedList, isSame);
        List<CouponStintRuleRef> addList = LangUtils.removeIfExits(entities, mixedList, isSame);

        // 删除
        this.removeByIds(LangUtils.toList(deleteList, CouponStintRuleRef::getId));

        // 新增
        this.saveBatch(addList);

        // 更新
        mixedList.forEach(this::validInsertOrUpdate);
    }

    private List<CouponStintRuleRef> listByCouponId(Long couponId) {
        return lambdaQuery().eq(CouponStintRuleRef::getCouponId, couponId).list();
    }
}
