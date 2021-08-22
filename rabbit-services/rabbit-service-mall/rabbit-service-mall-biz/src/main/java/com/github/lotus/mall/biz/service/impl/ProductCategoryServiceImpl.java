package com.github.lotus.mall.biz.service.impl;

import com.github.lotus.mall.biz.entity.ProductCategory;
import com.github.lotus.mall.biz.mapper.ProductCategoryMapper;
import com.github.lotus.mall.biz.pojo.ro.ProductCategorySaveRo;
import com.github.lotus.mall.biz.pojo.ro.ProductCategoryTreeRo;
import com.github.lotus.mall.biz.pojo.ro.ProductCategoryUpdateRo;
import com.github.lotus.mall.biz.pojo.vo.ProductCategoryComplexVo;
import com.github.lotus.mall.biz.pojo.vo.ProductCategoryTreeVo;
import com.github.lotus.mall.biz.service.ProductCategoryService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [商品模块] 商品品类表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductCategoryServiceImpl extends AbstractServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Override
    public void insertOne(ProductCategorySaveRo ro) {

    }

    @Override
    public void updateOne(Long id, ProductCategorySaveRo ro) {

    }

    @Override
    public List<ProductCategoryTreeVo> listTree(ProductCategoryTreeRo ro) {
        return null;
    }


    @Override
    public ProductCategoryComplexVo getComplex(Long id) {
        return null;
    }

    @Override
    public void deleteAllById(Long id) {

    }
}
