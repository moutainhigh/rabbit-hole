package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.com.api.FileServiceApi;
import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import in.hocg.rabbit.com.api.enums.FileRelType;
import in.hocg.rabbit.mall.biz.entity.Product;
import in.hocg.rabbit.mall.biz.entity.Sku;
import in.hocg.rabbit.mall.biz.mapper.ProductMapper;
import in.hocg.rabbit.mall.biz.mapstruct.ProductMapping;
import in.hocg.rabbit.mall.biz.mapstruct.SkuMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductSaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.service.ProductService;
import in.hocg.rabbit.mall.biz.service.SkuService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductServiceImpl extends AbstractServiceImpl<ProductMapper, Product> implements ProductService {
    private final ProductMapping mapping;
    private final SkuMapping skuMapping;
    private final SkuService skuService;
    private final FileServiceApi fileServiceApi;

    @Override
    public void insertOne(ProductSaveRo ro) {
        this.saveOne(null, ro);
    }

    @Override
    public void updateOne(Long id, ProductSaveRo ro) {
        this.saveOne(id, ro);
    }

    @Override
    public void deleteOne(Long id) {
        Product update = new Product();
        update.setId(id);
        update.setDeleteFlag(id);
        this.validUpdateById(update);
    }

    @Override
    public ProductComplexVo getComplexById(Long id) {
        return this.convertComplex(getById(id));
    }

    @Override
    public IPage<ProductComplexVo> paging(ProductPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertComplex);
    }

    @Override
    public List<ProductComplexVo> complete(ProductCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).convert(this::convertComplex).getRecords();
    }

    @Override
    public ProductComplexVo convertComplex(Product entity) {
        ProductComplexVo result = mapping.asProductComplexVo(entity);
        result.setDeleteFlag(Objects.nonNull(entity.getDeleteFlag()));
        return result;
    }

    private void saveOne(Long id, ProductSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Product entity = mapping.asProduct(ro);
        final Long userId = ro.getUserId();
        if (Objects.isNull(id)) {
            entity.setCreatedAt(now);
            entity.setCreator(userId);
        } else {
            entity.setLastUpdatedAt(now);
            entity.setLastUpdater(userId);
        }
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
                .setCreator(userId)
                .setRefId(productId)
                .setFiles(photos);
            fileServiceApi.upload(uploadFileRo);
        }
    }
}
