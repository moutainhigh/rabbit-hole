package in.hocg.rabbit.mall.biz.convert;

import com.alibaba.fastjson.JSON;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.com.api.FileServiceApi;
import in.hocg.rabbit.com.api.enums.FileRelType;
import in.hocg.rabbit.com.api.pojo.vo.FileVo;
import in.hocg.rabbit.mall.biz.entity.Product;
import in.hocg.rabbit.mall.biz.mapstruct.ProductMapping;
import in.hocg.rabbit.mall.biz.mapstruct.SkuMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductOrdinaryVo;
import in.hocg.rabbit.mall.biz.pojo.vo.SkuComplexVo;
import in.hocg.rabbit.mall.biz.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hocgin on 2022/1/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductConvert {
    private final ProductMapping mapping;
    private final SkuService skuService;
    private final FileServiceApi fileServiceApi;

    public ProductComplexVo asProductComplexVo(Product entity) {
        Long entityId = entity.getId();
        String attrsStr = entity.getAttrs();
        ProductComplexVo result = mapping.asProductComplexVo(entity);

        List<SkuComplexVo> skus = LangUtils.toList(skuService.listByProductId(entityId),
            sku -> skuService.as(sku, SkuComplexVo.class));

        final List<FileVo> photos = fileServiceApi.listByRefTypeAndRefId(FileRelType.Product.getCodeStr(), entityId);
        result.setImages(photos);

        if (!CollectionUtils.isEmpty(photos)) {
            result.setMainImage(photos.get(0).getUrl());
        }

        if (!CollectionUtils.isEmpty(skus)) {
            final BigDecimal lowestAmt = skus.parallelStream().min(Comparator.comparing(SkuComplexVo::getUnitPrice))
                .map(SkuComplexVo::getUnitPrice).orElse(null);
            final BigDecimal highestAmt = skus.parallelStream().max(Comparator.comparing(SkuComplexVo::getUnitPrice))
                .map(SkuComplexVo::getUnitPrice).orElse(null);
            result.setLowestAmt(lowestAmt);
            result.setHighestAmt(highestAmt);
        }

        return result.setSku(skus)
            .setAttrs(JSON.parseArray(attrsStr, KeyValue.class));
    }

    public ProductOrdinaryVo asProductOrdinaryVo(Product entity) {
        String attrsStr = entity.getAttrs();
        return mapping.asProductOrdinaryVo(entity)
            .setAttrs(JSON.parseArray(attrsStr, KeyValue.class));
    }

}
