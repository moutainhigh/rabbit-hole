package com.github.lotus.mall.biz.mapstruct;

import com.github.lotus.mall.biz.entity.Product;
import com.github.lotus.mall.biz.pojo.ro.ProductSaveRo;
import com.github.lotus.mall.biz.pojo.vo.ProductComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ProductMapping {
    @Mapping(target = "deleteFlag", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product asProduct(ProductSaveRo ro);

    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "minPrice", ignore = true)
    @Mapping(target = "maxPrice", ignore = true)
    @Mapping(target = "mainPhotoUrl", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "deleteFlag", ignore = true)
    ProductComplexVo asProductComplexVo(Product entity);
}
