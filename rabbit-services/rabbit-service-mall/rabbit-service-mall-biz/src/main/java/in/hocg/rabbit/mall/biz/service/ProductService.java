package in.hocg.rabbit.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.rabbit.mall.biz.entity.Product;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductSaveRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [商品模块] 商品表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface ProductService extends AbstractService<Product> {

    void insertOne(ProductSaveRo ro);

    void updateOne(Long id, ProductSaveRo ro);

    void delete(DeleteRo ro);

    ProductComplexVo getComplexById(Long id);

    IPage<ProductComplexVo> paging(ProductPagingRo ro);

    List<ProductComplexVo> complete(ProductCompleteRo ro);

    ProductComplexVo convertComplex(Product entity);
}
