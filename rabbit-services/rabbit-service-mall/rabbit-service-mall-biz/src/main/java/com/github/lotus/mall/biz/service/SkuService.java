package com.github.lotus.mall.biz.service;

import com.github.lotus.mall.biz.entity.Sku;
import com.github.lotus.mall.biz.pojo.vo.SkuComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * [商品模块] 商品SKU表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface SkuService extends AbstractService<Sku> {

    void insertOrUpdateByProductId(Long productId, List<Sku> skuList);

    SkuComplexVo getComplexById(Long skuId);

    boolean casValidAndSubtractStock(Long skuId, Integer useStock);

    boolean casValidAndPlusStock(Long skuId, Integer useStock);
}
