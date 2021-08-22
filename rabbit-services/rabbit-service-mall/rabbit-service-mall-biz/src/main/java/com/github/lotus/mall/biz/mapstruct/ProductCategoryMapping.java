package com.github.lotus.mall.biz.mapstruct;

import com.github.lotus.mall.biz.entity.ProductCategory;
import com.github.lotus.mall.biz.pojo.ro.ProductCategorySaveRo;
import com.github.lotus.mall.biz.pojo.vo.ProductCategoryComplexVo;
import com.github.lotus.mall.biz.pojo.vo.ProductCategoryTreeVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ProductCategoryMapping {

    ProductCategory asProductCategory(ProductCategorySaveRo ro);

    ProductCategoryComplexVo asProductCategoryComplexVo(ProductCategory entity);

    ProductCategoryTreeVo asProductCategoryTreeVo(ProductCategory productCategory);
}
