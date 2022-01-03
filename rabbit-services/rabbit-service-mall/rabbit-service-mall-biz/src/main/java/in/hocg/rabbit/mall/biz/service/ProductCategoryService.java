package in.hocg.rabbit.mall.biz.service;

import in.hocg.rabbit.mall.biz.entity.ProductCategory;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCategorySaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCategoryTreeRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryTreeVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [商品模块] 商品品类表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface ProductCategoryService extends AbstractService<ProductCategory> {

    void insertOne(ProductCategorySaveRo ro);

    void updateOne(Long id, ProductCategorySaveRo ro);

    List<ProductCategoryTreeVo> listTree(ProductCategoryTreeRo ro);

    ProductCategoryComplexVo getComplex(Long id);

    void deleteAllById(Long id);

    ProductCategoryComplexVo convertComplex(ProductCategory entity);
}
