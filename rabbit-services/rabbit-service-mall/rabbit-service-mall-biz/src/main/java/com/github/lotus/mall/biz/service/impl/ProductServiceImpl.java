package com.github.lotus.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.Product;
import com.github.lotus.mall.biz.mapper.ProductMapper;
import com.github.lotus.mall.biz.pojo.ro.ProductCompleteRo;
import com.github.lotus.mall.biz.pojo.ro.ProductSaveRo;
import com.github.lotus.mall.biz.pojo.ro.ProductPagingRo;
import com.github.lotus.mall.biz.pojo.vo.ProductComplexVo;
import com.github.lotus.mall.biz.service.ProductService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [商品模块] 商品表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductServiceImpl extends AbstractServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public void insertOne(ProductSaveRo ro) {

    }

    @Override
    public void updateOne(Long id, ProductSaveRo ro) {

    }

    @Override
    public void deleteOne(Long id) {

    }

    @Override
    public ProductComplexVo getComplexById(Long id) {
        return null;
    }

    @Override
    public IPage<ProductComplexVo> paging(ProductPagingRo ro) {
        return null;
    }

    @Override
    public List<ProductComplexVo> complete(ProductCompleteRo ro) {
        return null;
    }

}
