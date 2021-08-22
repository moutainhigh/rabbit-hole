package com.github.lotus.mall.biz.mapstruct;

import com.github.lotus.mall.biz.entity.Sku;
import com.github.lotus.mall.biz.pojo.ro.ProductSaveRo;
import com.github.lotus.mall.biz.pojo.vo.SkuComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface SkuMapping {
    @Mapping(target = "specData", ignore = true)
    @Mapping(target = "sale", ignore = true)
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "id", ignore = true)
    Sku asSku(ProductSaveRo.Sku sku);

    SkuComplexVo asSkuComplexVo(Sku entity);
}
