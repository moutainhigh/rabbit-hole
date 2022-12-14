package in.hocg.rabbit.mall.biz.service.impl;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.rabbit.mall.biz.convert.SkuConvert;
import in.hocg.rabbit.mall.biz.entity.Sku;
import in.hocg.rabbit.mall.biz.mapper.SkuMapper;
import in.hocg.rabbit.mall.biz.mapstruct.SkuMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.SkuComplexVo;
import in.hocg.rabbit.mall.biz.service.SkuService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
@UseConvert(SkuConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SkuServiceImpl extends AbstractServiceImpl<SkuMapper, Sku> implements SkuService {
    private final SkuMapping mapping;
    private final SkuConvert convert;

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
        return convert.asSkuComplexVo(getById(skuId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 1, rollbackFor = Exception.class)
    public boolean casValidAndSubtractStock(Long skuId, Integer useStock) {
        final Sku sku = baseMapper.selectById(skuId);
        final Integer stock = sku.getStock();
        if (stock < useStock) {
            return false;
        }
        final boolean isOk = baseMapper.subtractStock(skuId, useStock, stock) > 0;
        if (isOk) {
            return true;
        }
        return ((SkuService) AopContext.currentProxy()).casValidAndSubtractStock(skuId, useStock);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 1, rollbackFor = Exception.class)
    public boolean casValidAndPlusStock(Long skuId, Integer useStock) {
        final Sku sku = baseMapper.selectById(skuId);
        final boolean isOk = baseMapper.plusStock(skuId, useStock, sku.getStock()) > 0;
        if (isOk) {
            return true;
        }
        return ((SkuService) AopContext.currentProxy()).casValidAndPlusStock(skuId, useStock);
    }

    @Override
    public List<Sku> listByProductId(Long productId) {
        return lambdaQuery().eq(Sku::getProductId, productId).list();
    }
}
