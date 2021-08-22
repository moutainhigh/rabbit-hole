package com.github.lotus.mall.biz.mapper;

import com.github.lotus.mall.biz.entity.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [商品模块] 商品SKU表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 补充库存
     *
     * @param skuId    SKU ID
     * @param useStock 要使用的库存数量
     * @param preStock 原库存数量
     * @return
     */
    Integer plusStock(@Param("skuId") Long skuId, @Param("useStock") Integer useStock, @Param("preStock") Integer preStock);

    /**
     * 减掉库存
     *
     * @param skuId    SKU ID
     * @param useStock 要使用的库存数量
     * @param preStock 原库存数量
     * @return
     */
    Integer subtractStock(@Param("skuId") Long skuId, @Param("useStock") Integer useStock, @Param("preStock") Integer preStock);
}
