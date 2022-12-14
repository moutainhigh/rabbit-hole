package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.com.api.FileServiceApi;
import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import in.hocg.rabbit.com.api.enums.FileRelType;
import in.hocg.rabbit.mall.biz.convert.ProductConvert;
import in.hocg.rabbit.mall.biz.entity.Product;
import in.hocg.rabbit.mall.biz.entity.Sku;
import in.hocg.rabbit.mall.biz.mapper.ProductMapper;
import in.hocg.rabbit.mall.biz.mapstruct.ProductMapping;
import in.hocg.rabbit.mall.biz.mapstruct.SkuMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductSaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.ProductService;
import in.hocg.rabbit.mall.biz.service.SkuService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * [商品模块] 商品表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@UseConvert(ProductConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductServiceImpl extends AbstractServiceImpl<ProductMapper, Product> implements ProductService {
    private final ProductMapping mapping;
    private final ProductConvert convert;
    private final SkuMapping skuMapping;
    private final SkuService skuService;
    private final FileServiceApi fileServiceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(ProductSaveRo ro) {
        this.saveOne(null, ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, ProductSaveRo ro) {
        this.saveOne(id, ro);
    }

    @Override
    public void delete(DeleteRo ro) {
        removeByIds(ro.getId());
    }

    @Override
    public ProductComplexVo getComplex(Long id) {
        return convert.asProductComplexVo(getById(id));
    }

    @Override
    public IPage<ProductOrdinaryVo> paging(ProductPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(convert::asProductOrdinaryVo);
    }

    @Override
    public List<ProductOrdinaryVo> complete(ProductCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).convert(convert::asProductOrdinaryVo).getRecords();
    }

    private void saveOne(Long id, ProductSaveRo ro) {
        Product entity = mapping.asProduct(ro);
        entity.setAttrs(JSONUtil.toJsonStr(ro.getAttrs()));
        entity.setId(id);
        validInsertOrUpdate(entity);
        final Long productId = entity.getId();

        // 设置SKU
        final List<ProductSaveRo.Sku> allSku = ro.getSku();
        if (Objects.nonNull(allSku)) {
            List<Sku> skuList = LangUtils.toList(allSku, sku -> skuMapping.asSku(sku).setProductId(productId).setSpecData(JSONUtil.toJsonStr(sku.getSpec())));
            skuService.insertOrUpdateByProductId(productId, skuList);
        }

        // 设置图片
        final List<UploadFileRo.FileDto> photos = ro.getImages();
        if (Objects.nonNull(photos)) {
            UploadFileRo uploadFileRo = new UploadFileRo()
                .setRefType(FileRelType.Product.getCodeStr())
                .setRefId(productId)
                .setFiles(photos);
            fileServiceApi.upload(uploadFileRo);
        }
    }
}
