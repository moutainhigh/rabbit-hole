package in.hocg.rabbit.mall.biz.service.impl;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.rabbit.mall.biz.convert.ProductCategoryConvert;
import in.hocg.rabbit.mall.biz.entity.ProductCategory;
import in.hocg.rabbit.mall.biz.mapper.ProductCategoryMapper;
import in.hocg.rabbit.mall.biz.mapstruct.ProductCategoryMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCategorySaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCategoryTreeRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryTreeVo;
import in.hocg.rabbit.mall.biz.service.ProductCategoryService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.tree.TreeServiceImpl;
import in.hocg.boot.web.datastruct.tree.Tree;
import in.hocg.boot.utils.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * [商品模块] 商品品类表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@UseConvert(ProductCategoryConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductCategoryServiceImpl extends TreeServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
    private final ProductCategoryMapping mapping;
    private final ProductCategoryConvert convert;

    @Override
    public void insertOne(ProductCategorySaveRo ro) {
        this.saveOne(null, ro);
    }

    @Override
    public void updateOne(Long id, ProductCategorySaveRo ro) {
        this.saveOne(id, ro);
    }

    @Override
    public List<ProductCategoryTreeVo> listTree(ProductCategoryTreeRo ro) {
        final Long parentId = ro.getParentId();
        List<ProductCategory> all = baseMapper.listTree(ro);
        return Tree.getChild(parentId, all.parallelStream().map(mapping::asProductCategoryTreeVo)
            .collect(Collectors.toList()));
    }


    @Override
    public ProductCategoryComplexVo getComplex(Long id) {
        return convert.asProductCategoryComplexVo(getById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(DeleteRo ro) {
        ro.getId().forEach(this::deleteAllById);
    }

    private void deleteAllById(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        final ProductCategory entity = getById(id);
        if (Objects.isNull(entity)) {
            return;
        }

        final String regexTreePath = String.format("%s.*?", entity.getTreePath());
        if (isUsedProduct(regexTreePath)) {
            throw ServiceException.wrap("商品品类正在被商品使用");
        }
        if (isUsedCoupon(regexTreePath)) {
            throw ServiceException.wrap("商品品类正在被优惠券使用");
        }

        super.deleteCurrentAndChildren(id);
    }


    private void saveOne(Long id, ProductCategorySaveRo ro) {
        ProductCategory entity = mapping.asProductCategory(ro);
        entity.setId(id);

        validInsertOrUpdate(entity);
    }

    private boolean isUsedProduct(String regexTreePath) {
        return Objects.nonNull(baseMapper.countUsedProduct(regexTreePath));
    }

    private boolean isUsedCoupon(String regexTreePath) {
        return Objects.nonNull(baseMapper.countUsedCoupon(regexTreePath));
    }

}
