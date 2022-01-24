package in.hocg.rabbit.mall.biz.service;

import in.hocg.rabbit.mall.biz.entity.CouponStintRuleRef;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [促销模块] 优惠券-限制规则表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
public interface CouponStintRuleRefService extends AbstractService<CouponStintRuleRef> {

    boolean hasUsedStintRule(List<Long> ruleIds);

    void saveBatchByCouponId(Long couponId, List<Long> ruleId);
}
