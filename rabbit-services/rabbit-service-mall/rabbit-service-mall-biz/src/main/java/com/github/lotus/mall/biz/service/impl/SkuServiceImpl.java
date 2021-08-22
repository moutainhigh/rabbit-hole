package com.github.lotus.mall.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.lotus.mall.biz.entity.Sku;
import com.github.lotus.mall.biz.mapper.SkuMapper;
import com.github.lotus.mall.biz.mapstruct.SkuMapping;
import com.github.lotus.mall.biz.pojo.vo.SkuComplexVo;
import com.github.lotus.mall.biz.service.SkuService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * <p>
 * [商品模块] 商品SKU表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SkuServiceImpl extends AbstractServiceImpl<SkuMapper, Sku> implements SkuService {
    private final SkuMapping mapping;

    @Override
    public void insertOrUpdateByProductId(Long productId, List<Sku> entities) {
        final List<Sku> allData = this.listByProductId(productId);
        final BiFunction<Sku, Sku, Boolean> isSame = (t1, t2) -> LangUtils.equals(t1.getId(), t2.getId());
        final List<Sku> mixedList = LangUtils.getMixed(allData, entities, isSame);
        List<Sku> deleteList = LangUtils.removeIfExits(allData, mixedList, isSame);
        List<Sku> addList = LangUtils.removeIfExits(entities, mixedList, isSame);

        // 删除
        final List<Long> deleteIds = deleteList.parallelStream().map(Sku::getId)
            .collect(Collectors.toList());
        this.removeByIds(deleteIds);

        // 新增
        addList.forEach(this::validInsertOrUpdate);

        // 更新
        mixedList.forEach(this::validInsertOrUpdate);
    }

    @Override
    public SkuComplexVo getComplexById(Long skuId) {
        return this.convertComplex(getById(skuId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 1, rollbackFor = Exception.class)
    public boolean casValidAndSubtractStock(Long skuId, Integer useStock) {
        final Sku sku = baseMapper.selectById(skuId);
        final Integer stock = sku.getStock();
        if (stock < useStock) {
            return false;
        }
        final boolean isOk = this.retBool(baseMapper.subtractStock(skuId, useStock, stock));
        if (isOk) {
            return true;
        }
        return ((SkuService) AopContext.currentProxy()).casValidAndSubtractStock(skuId, useStock);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 1, rollbackFor = Exception.class)
    public boolean casValidAndPlusStock(Long skuId, Integer useStock) {
        final Sku sku = baseMapper.selectById(skuId);
        final boolean isOk = this.retBool(baseMapper.plusStock(skuId, useStock, sku.getStock()));
        if (isOk) {
            return true;
        }
        return ((SkuService) AopContext.currentProxy()).casValidAndPlusStock(skuId, useStock);
    }

    private SkuComplexVo convertComplex(Sku entity) {
        SkuComplexVo result = mapping.asSkuComplexVo(entity);
        result.setSpec(JSON.parseArray(entity.getSpecData(), SkuComplexVo.Spec.class));
        return result;
    }

    private List<Sku> listByProductId(Long productId) {
        return lambdaQuery().eq(Sku::getProductId, productId).list();
    }
}
