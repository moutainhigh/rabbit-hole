package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.mall.biz.convert.StintRuleConvert;
import in.hocg.rabbit.mall.biz.entity.StintRule;
import in.hocg.rabbit.mall.biz.mapper.StintRuleMapper;
import in.hocg.rabbit.mall.biz.mapstruct.StintRuleMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRuleCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRulePagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRuleSaveRo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.CouponStintRuleRefService;
import in.hocg.rabbit.mall.biz.service.StintRuleService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [促销模块] 限制规则表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Service
@UseConvert(StintRuleConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StintRuleServiceImpl extends AbstractServiceImpl<StintRuleMapper, StintRule> implements StintRuleService {
    private final StintRuleConvert convert;
    private final StintRuleMapping mapping;
    private final CouponStintRuleRefService couponStintRuleRefService;

    @Override
    public void insertOne(StintRuleSaveRo ro) {
        saveOne(null, ro);
    }

    @Override
    public void updateOne(Long id, StintRuleSaveRo ro) {
        saveOne(id, ro);
    }

    @Override
    public void delete(DeleteRo ro) {
        hasUsedStintRuleThrow(ro.getId());
        removeByIds(ro.getId());
    }

    @Override
    public StintRuleComplexVo getComplex(Long id) {
        return convert.convertStintRuleComplexVo(getById(id));
    }

    @Override
    public IPage<StintRuleOrdinaryVo> paging(StintRulePagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(convert::convertStintRuleOrdinaryVo);
    }

    @Override
    public List<StintRuleOrdinaryVo> complete(StintRuleCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage())
            .convert(convert::convertStintRuleOrdinaryVo).getRecords();
    }

    @Override
    public List<StintRule> listByCouponId(Long couponId) {
        return baseMapper.listByCouponId(couponId);
    }

    private void hasUsedStintRuleThrow(List<Long> id) {
        Assert.isFalse(couponStintRuleRefService.hasUsedStintRule(id), "该限制规则已被优惠券使用，无法删除");
    }

    private void saveOne(Long id, StintRuleSaveRo ro) {
        StintRule entity = mapping.asStintRule(ro);
        entity.setId(id);
        saveOrUpdate(entity);
    }
}
