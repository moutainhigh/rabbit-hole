package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.ProductCategory;
import in.hocg.rabbit.mall.biz.mapstruct.ProductCategoryMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryComplexVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductCategoryConvert {
    private final ProductCategoryMapping mapping;

    public ProductCategoryComplexVo asProductCategoryComplexVo(ProductCategory entity) {
        return mapping.asProductCategoryComplexVo(entity);
    }

}
