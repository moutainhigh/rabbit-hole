package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.Product;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductSaveRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductOrdinaryVo;
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
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "attrs", ignore = true)
    Product asProduct(ProductSaveRo ro);

    @Mapping(target = "unitName", ignore = true)
    @Mapping(target = "shopName", ignore = true)
    @Mapping(target = "mainImage", ignore = true)
    @Mapping(target = "lowestAmt", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "highestAmt", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "attrs", ignore = true)
    ProductComplexVo asProductComplexVo(Product entity);

    @Mapping(target = "mainImage", ignore = true)
    @Mapping(target = "shopName", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
    @Mapping(target = "attrs", ignore = true)
    ProductOrdinaryVo asProductOrdinaryVo(Product entity);
}
