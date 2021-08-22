package com.github.lotus.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.Product;
import com.github.lotus.mall.biz.pojo.ro.ProductCompleteRo;
import com.github.lotus.mall.biz.pojo.ro.ProductSaveRo;
import com.github.lotus.mall.biz.pojo.ro.ProductPagingRo;
import com.github.lotus.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [商品模块] 商品表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface ProductService extends AbstractService<Product> {

    void insertOne(ProductSaveRo ro);

    void updateOne(Long id, ProductSaveRo ro);

    void deleteOne(Long id);

    ProductComplexVo getComplexById(Long id);

    IPage<ProductComplexVo> paging(ProductPagingRo ro);

    List<ProductComplexVo> complete(ProductCompleteRo ro);
}
